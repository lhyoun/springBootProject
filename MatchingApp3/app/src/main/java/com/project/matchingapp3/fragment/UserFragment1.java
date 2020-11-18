package com.project.matchingapp3.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.matchingapp3.R;
import com.project.matchingapp3.activity.UserDetailActivity;
import com.project.matchingapp3.adapter.OnUserItemClickListener;
import com.project.matchingapp3.adapter.UserListAdapter;
import com.project.matchingapp3.model.User;
import com.project.matchingapp3.model.dto.NavDataDto;

import java.util.ArrayList;
import java.util.List;

public class UserFragment1 extends Fragment {

    private ArrayList<User> users = new ArrayList<User>();
    private User loginUser;
    private String jwtToken;

    private RecyclerView recyclerView;
    UserListAdapter adapter;

    public UserFragment1(List<User> users, User loginUser, String jwtToken){
        this.users = (ArrayList<User>) users;
        this.loginUser = loginUser;
        this.jwtToken = jwtToken;

        //this.users.addAll(users);
        Log.e("noitem-유저리스트프래그받음?", users.toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.user_fragment1, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);

        //리사이클러뷰에 설정할 레이아웃 매니저 - 방향세로로 설정함.
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new UserListAdapter();
        adapter.setItems(users);
        Log.e("noitem-유저어댑터관리 아이템개수",":"+adapter.getItemCount());

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnUserItemClickListener() {
            @Override
            public void onItemClick(UserListAdapter.ViewHolder holder, View view, int position) {
                User item = adapter.getItem(position);

                Intent intent = new Intent(getContext(), UserDetailActivity.class);
                intent.putExtra("jwtToken", jwtToken);
                intent.putExtra("loginUser", loginUser);
                intent.putExtra("selectUser", item);

//                if(item.getTeams() != null){
//                    intent.putExtra("selectUserTeam", item.getTeams().getName());
//                    if(item.getId() == item.getTeams().getOwner().getId()){
//                        intent.putExtra("selectUserRole", "Owner");
//                    }
//                }
                startActivity(intent);
            }
        });

        return rootView;
        //return inflater.inflate(R.layout.team_fragment1, container, false);
    }
}