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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.project.matchingapp3.R;
import com.project.matchingapp3.TeamActivity;
import com.project.matchingapp3.activity.BattleActivity;
import com.project.matchingapp3.activity.TeamDetailActivity;
import com.project.matchingapp3.adapter.NoticeBListAdapter;
import com.project.matchingapp3.adapter.OnNoticeBClickListener;
import com.project.matchingapp3.model.Battle;
import com.project.matchingapp3.model.User;

import java.util.ArrayList;

public class PartyListFragment2 extends Fragment {

    private ArrayList<Battle> battleList;
    private User loginUser;
    private String jwtToken;

    RecyclerView recyclerView;
    NoticeBListAdapter adapter;

    public PartyListFragment2(){}

    public PartyListFragment2(ArrayList<Battle> battleList, User loginUser, String jwtToken){
        this.battleList = battleList;
        this.loginUser = loginUser;
        this.jwtToken = jwtToken;

        //this.users.addAll(party);
        if(battleList != null && battleList.size() != 0){
            Log.e("test-partyList프래그먼트2", "배틀리스트 받음 : " + battleList.toString());
        }else{
            Log.e("test-partyList프래그먼트2", "배틀리스트 안받았다");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //battle리스트 없을시 띄울 화면
        if(battleList == null || battleList.size() == 0){
            final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.noitem, container, false);
            TextView tvMsg = rootView.findViewById(R.id.noitem_tv_message);
            Button btnPage = rootView.findViewById(R.id.noitem_btn_teampage);
            ImageView ivImage = rootView.findViewById(R.id.noitem_iv_image);
            ivImage.setImageResource(R.drawable.icon_noitem2);

            if(loginUser.getTeams() == null){
                tvMsg.setText("받은 경기 요청이 없습니다. 팀을 먼저 가입해주세요.");
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
                tvMsg.setText("받은 경기 요청이 없습니다.");
                btnPage.setText("매치 신청하러 가기");
                btnPage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(rootView.getContext(), TeamActivity.class);
                        intent.putExtra("jwtToken", jwtToken);
                        intent.putExtra("loginUser", loginUser);
                        startActivity(intent);
                    }
                });
            }

            return rootView;
        }

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_party_list2, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);

        //리사이클러뷰에 설정할 레이아웃 매니저 - 방향세로로 설정함.
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new NoticeBListAdapter(loginUser.getTeams().getId());
        adapter.setItems(battleList);
        Log.e("test-partyList프래그먼트", "배틀 리스트 어댑터 관리수:" + adapter.getItemCount());

        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnNoticeBClickListener() {
            //상대팀 상세보기
            @Override
            public void onItemClick(NoticeBListAdapter.ViewHolder holder, View view, int position) {
                Battle item = adapter.getItem(position);

                Intent intent = new Intent(getContext(), TeamDetailActivity.class);
                intent.putExtra("jwtToken", jwtToken);
                intent.putExtra("loginUser", loginUser);
                if(loginUser.getTeams().getId() == item.getResponseTeam().getId()){
                    intent.putExtra("selectTeam", item.getRequestTeam());
                }else{
                    intent.putExtra("selectTeam", item.getResponseTeam());
                }

                startActivity(intent);
            }
        }, new OnNoticeBClickListener() {
            //경기 수락하기
            @Override
            public void onItemClick(NoticeBListAdapter.ViewHolder holder, View view, int position) {
                Battle item = adapter.getItem(position);
                if(loginUser.getTeams().getOwner().getId() != loginUser.getId()){
                    Snackbar.make(view, "경기 수락 실패. 구단주만 수락 가능합니다.", Snackbar.LENGTH_SHORT).show();
                    return ;
                }
                //매치 수락 페이지로 이동
                Intent intent = new Intent(getContext(), BattleActivity.class);
                intent.putExtra("jwtToken", jwtToken);
                intent.putExtra("loginUser", loginUser);
                intent.putExtra("selectTeam", item.getRequestTeam());
                intent.putExtra("selectBattle", item);
                intent.putExtra("role", 1);
                startActivity(intent);
            }
        });

        return rootView;
    }
}