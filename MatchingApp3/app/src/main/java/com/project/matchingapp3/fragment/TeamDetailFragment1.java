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
import android.widget.Toast;

import com.project.matchingapp3.R;
import com.project.matchingapp3.activity.TeamDetailActivity;
import com.project.matchingapp3.activity.UserDetailActivity;
import com.project.matchingapp3.adapter.OnPartyTClickListener;
import com.project.matchingapp3.adapter.OnTDetailUClickListener;
import com.project.matchingapp3.adapter.PartyTeamAdapter;
import com.project.matchingapp3.adapter.PartyUserAdapter;
import com.project.matchingapp3.adapter.TeamDetailUsersAdapter;
import com.project.matchingapp3.model.Party;
import com.project.matchingapp3.model.User;
import com.project.matchingapp3.task.RestAPITask;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class TeamDetailFragment1 extends Fragment {

    private ArrayList<User> users;
    private User loginUser;
    private String jwtToken;

    RecyclerView recyclerView;
    TeamDetailUsersAdapter adapter;

    public TeamDetailFragment1(){}

    public TeamDetailFragment1(ArrayList<User> users, User loginUser, String jwtToken){
        this.users = users;
        this.loginUser = loginUser;
        this.jwtToken = jwtToken;

        //this.users.addAll(party);
        if(users != null && users.size() != 0){
            Log.e("test-TeamDetail프래그먼트1", "유저 리스트받음 : " + users.toString());
        }else{
            Log.e("test-TeamDetail프래그먼트1", "유저 리스트 안받았다");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_team_detail, container, false);

//        if(users.size() != 0 && users != null) {
//            if (loginUser.getId() == loginUser.getTeams().getOwner().getId() &&
//                    loginUser.getId() == users.get(0).getTeams().getOwner().getId()) {
//                Button btnBan = rootView.findViewById(R.id.tDetail_item_btn_ban);
//                btnBan.setVisibility(View.VISIBLE);
//            }
//        }

        recyclerView = rootView.findViewById(R.id.recyclerView);
        //리사이클러뷰에 설정할 레이아웃 매니저 - 방향세로로 설정함.
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new TeamDetailUsersAdapter();
        adapter.setItems(users);
        Log.e("test-TeamDetail프래그먼트1", "유저 어댑터 관리수:" + adapter.getItemCount());

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnTDetailUClickListener() {
            @Override
            public void onItemClick(TeamDetailUsersAdapter.ViewHolder holder, View view, int position) {
                //유저 정보 클릭
                User item = adapter.getItem(position);

                Intent intent = new Intent(getContext(), UserDetailActivity.class);
                intent.putExtra("jwtToken", jwtToken);
                intent.putExtra("loginUser", loginUser);
                intent.putExtra("selectUser", item);

                startActivity(intent);
            }
        }, new OnTDetailUClickListener() {
            @Override
            public void onItemClick(TeamDetailUsersAdapter.ViewHolder holder, View view, int position) {
                //추방 클릭
//                User item = adapter.getItem(position);
//
//                String[] result = new String[1];
//                RestAPITask task = new RestAPITask(jwtToken);
//                try {
//                    result = task.execute("Acknowledgment/", Integer.toString(item.getId())).get();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                Log.e("test-partyList프래그먼트", "팀가입요청 결과 : " + result[0]);
//
//                if (result[0].equals("ok")) {
//                    users.remove(position);
//                    adapter.notifyItemRemoved(position);
//                    adapter.notifyItemRangeChanged(position, users.size());
//                    Toast.makeText(view.getContext(), "가입 수락 완료.", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(view.getContext(), "에러.  " + result[0], Toast.LENGTH_SHORT).show();
//                }
            }
        });

        return rootView;
    }
}