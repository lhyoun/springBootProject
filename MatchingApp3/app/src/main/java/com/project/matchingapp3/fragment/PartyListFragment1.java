package com.project.matchingapp3.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.project.matchingapp3.R;
import com.project.matchingapp3.TeamActivity;
import com.project.matchingapp3.UserActivity;
import com.project.matchingapp3.activity.TeamDetailActivity;
import com.project.matchingapp3.activity.UserDetailActivity;
import com.project.matchingapp3.adapter.OnPartyTClickListener;
import com.project.matchingapp3.adapter.OnPartyUClickListener;
import com.project.matchingapp3.adapter.PartyTeamAdapter;
import com.project.matchingapp3.adapter.PartyUserAdapter;
import com.project.matchingapp3.model.Party;
import com.project.matchingapp3.model.User;
import com.project.matchingapp3.task.RestAPITask;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class PartyListFragment1 extends Fragment {

    private ArrayList<Party> partyList;
    private User loginUser;
    private String jwtToken;

    RecyclerView recyclerView;
    PartyUserAdapter adapterUser;
    PartyTeamAdapter adapterTeam;

    public PartyListFragment1(){}

    public PartyListFragment1(ArrayList<Party> partyList, User loginUser, String jwtToken){
        this.partyList = partyList;
        this.loginUser = loginUser;
        this.jwtToken = jwtToken;

        //this.users.addAll(party);
        if(partyList != null && partyList.size() != 0){
            Log.e("test-partyList프래그먼트1", "파티리스트받음 : " + partyList.toString());
        }else{
            Log.e("test-partyList프래그먼트1", "파티리스트프래그 안받았다");
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //party리스트 없을시 띄울 화면
        if(partyList == null || partyList.size() == 0){
            final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.noitem, container, false);
            TextView tvMsg = rootView.findViewById(R.id.noitem_tv_message);
            Button btnPage = rootView.findViewById(R.id.noitem_btn_teampage);

            if(loginUser.getTeams() == null){
                tvMsg.setText("받은 스카웃 제의가 없습니다.");
                btnPage.setText("팀 가입하러 가기");
                btnPage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(rootView.getContext(), TeamActivity.class);
                        intent.putExtra("jwtToken", jwtToken);
                        intent.putExtra("loginUser", loginUser);
                        startActivity(intent);
                    }
                });
            }else{
                tvMsg.setText("받은 팀 가입 신청이 없습니다.");
                btnPage.setText("스카웃 하러 가기");
                btnPage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(rootView.getContext(), UserActivity.class);
                        intent.putExtra("jwtToken", jwtToken);
                        intent.putExtra("loginUser", loginUser);
                        startActivity(intent);
                    }
                });
            }
            return rootView;
        }

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_party_list1, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        //리사이클러뷰에 설정할 레이아웃 매니저 - 방향세로로 설정함.
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        //팀없을시 팀 초대 리스트
        if (loginUser.getTeams() == null){

            adapterTeam = new PartyTeamAdapter();
            adapterTeam.setItems(partyList);
            Log.e("test-partyList프래그먼트1", "파티 팀 어댑터 관리수:" + adapterTeam.getItemCount());

            recyclerView.setAdapter(adapterTeam);

            adapterTeam.setOnItemClickListener(new OnPartyTClickListener() {
                @Override
                public void onItemClick(PartyTeamAdapter.ViewHolder holder, View view, int position) {
                    //팀 정보 클릭
                    Party item = adapterTeam.getItem(position);

                    Intent intent = new Intent(getContext(), TeamDetailActivity.class);
                    intent.putExtra("jwtToken", jwtToken);
                    intent.putExtra("loginUser", loginUser);
                    intent.putExtra("selectTeam", item.getTeam());

                    startActivity(intent);
                }
            }, new OnPartyTClickListener() {
                @Override
                public void onItemClick(PartyTeamAdapter.ViewHolder holder, View view, int position) {
                    //스카웃 수락클릭
                    Party item = adapterTeam.getItem(position);

                    String[] result = new String[1];
                    RestAPITask task = new RestAPITask(jwtToken);
                    try {
                        result = task.execute("Acknowledgment/", Integer.toString(item.getId())).get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Log.e("test-partyList프래그먼트", "팀가입요청 결과 : " + result[0]);

                    if(result[0].equals("ok")){
                        partyList.remove(position);
                        adapterTeam.notifyItemRemoved(position);
                        adapterTeam.notifyItemRangeChanged(position, partyList.size());
                        Toast.makeText(view.getContext(), "가입 수락 완료.",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(view.getContext(), "에러.  " + result[0],Toast.LENGTH_SHORT).show();
                    }
                }
            });
        //팀있을시 팀 가입신청 리스트
        }else{
            adapterUser = new PartyUserAdapter();
            adapterUser.setItems(partyList);
            Log.e("test-partyList프래그먼트1", "파티 유저 어댑터 관리수:" + adapterUser.getItemCount());

            recyclerView.setAdapter(adapterUser);

            adapterUser.setOnItemClickListener(new OnPartyUClickListener() {
                @Override   //상세보기 클릭시
                public void onItemClick(PartyUserAdapter.ViewHolder holder, View view, int position) {
                    //유저 정보 클릭
                    Party item = adapterUser.getItem(position);

                    Intent intent = new Intent(getContext(), UserDetailActivity.class);
                    intent.putExtra("jwtToken", jwtToken);
                    intent.putExtra("loginUser", loginUser);
                    intent.putExtra("selectUser", item.getUser());

                    startActivity(intent);
                }
                //가입 수락 버튼 클릭시
            }, new OnPartyUClickListener() {
                @Override
                public void onItemClick(PartyUserAdapter.ViewHolder holder, View view, int position) {
                    //가입 승인 클릭

                    if(loginUser.getId() != loginUser.getTeams().getOwner().getId()){
                        Snackbar.make(view, "구단주만 승인 가능합니다.", Snackbar.LENGTH_SHORT).show();
                        return;
                    }

                    Party item = adapterUser.getItem(position);

                    String[] result = new String[1];
                    RestAPITask task = new RestAPITask(jwtToken);
                    try {
                        result = task.execute("Acknowledgment/", Integer.toString(item.getId())).get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Log.e("test-partyList프래그먼트", "팀가입요청 결과 : " + result[0]);

                    if(result[0].equals("ok")){
                        partyList.remove(position);
                        adapterUser.notifyItemRemoved(position);
                        adapterUser.notifyItemRangeChanged(position, partyList.size());
                        Toast.makeText(view.getContext(), "가입 승인 완료.",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(view.getContext(), "에러.  " + result[0],Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        return rootView;
    }
}