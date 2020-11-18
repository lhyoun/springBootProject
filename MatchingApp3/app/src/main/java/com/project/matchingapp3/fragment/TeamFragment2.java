package com.project.matchingapp3.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.matchingapp3.R;
import com.project.matchingapp3.activity.TeamDetailActivity;
import com.project.matchingapp3.adapter.NoticeBListAdapter;
import com.project.matchingapp3.adapter.OnScoreTClickListener;
import com.project.matchingapp3.adapter.ScoreTeamAdapter;
import com.project.matchingapp3.model.Party;
import com.project.matchingapp3.model.Score;
import com.project.matchingapp3.model.Team;
import com.project.matchingapp3.model.User;

import java.util.ArrayList;

public class TeamFragment2 extends Fragment {

    private ArrayList<Score> scoreList = new ArrayList<Score>();
    private User loginUser;
    private String jwtToken;

    private RecyclerView recyclerView;
    private ScoreTeamAdapter adapter;

    public TeamFragment2(){}

    public TeamFragment2(ArrayList<Score> scoreList, User loginUser, String jwtToken){
        this.scoreList = scoreList;
        this.loginUser = loginUser;
        this.jwtToken = jwtToken;

        //this.users.addAll(party);
        if(scoreList != null && scoreList.size() != 0){
            Log.e("test-Team프래그먼트2", "랭킹 리스트 받음 : " + scoreList.toString());
        }else{
            Log.e("test-Team프래그먼트2", "랭킹 리스트 안받았다");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.team_fragment2, container, false);

        ImageView ivImage1 = rootView.findViewById(R.id.fTeam_iv_image1);
        ImageView ivImage2 = rootView.findViewById(R.id.fTeam_iv_image2);
        ImageView ivImage3 = rootView.findViewById(R.id.fTeam_iv_image3);
        TextView tvName1 = rootView.findViewById(R.id.fTeam_tv_name1);
        TextView tvName2 = rootView.findViewById(R.id.fTeam_tv_name2);
        TextView tvName3 = rootView.findViewById(R.id.fTeam_tv_name3);
        TextView tvScore1 = rootView.findViewById(R.id.fTeam_tv_score1);
        TextView tvScore2 = rootView.findViewById(R.id.fTeam_tv_score2);
        TextView tvScore3 = rootView.findViewById(R.id.fTeam_tv_score3);
        Glide.with(rootView).load(scoreList.get(0).getTeam().getUrlImage()).into(ivImage1);
        Glide.with(rootView).load(scoreList.get(1).getTeam().getUrlImage()).into(ivImage2);
        Glide.with(rootView).load(scoreList.get(2).getTeam().getUrlImage()).into(ivImage3);
        tvName1.setText(scoreList.get(0).getTeam().getName());
        tvName2.setText(scoreList.get(1).getTeam().getName());
        tvName3.setText(scoreList.get(2).getTeam().getName());
        tvScore1.setText(scoreList.get(0).getSummary());
        tvScore2.setText(scoreList.get(1).getSummary());
        tvScore3.setText(scoreList.get(2).getSummary());


        recyclerView = rootView.findViewById(R.id.recyclerView);
        //리사이클러뷰에 설정할 레이아웃 매니저 - 방향세로로 설정함.
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ScoreTeamAdapter();
        adapter.setItems(scoreList);
        Log.e("test-Team프래그먼트2", "랭킹 리스트 어댑터 관리수:" + adapter.getItemCount());

        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnScoreTClickListener() {
            @Override
            public void onItemClick(ScoreTeamAdapter.ViewHolder holder, View view, int position) {
                Score item = adapter.getItem(position);

                Intent intent = new Intent(getContext(), TeamDetailActivity.class);
                intent.putExtra("jwtToken", jwtToken);
                intent.putExtra("loginUser", loginUser);
                intent.putExtra("selectTeam", item.getTeam());
                startActivity(intent);
            }
        });

        return rootView;
    }
}