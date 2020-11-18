package com.project.matchingapp3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.matchingapp3.R;
import com.project.matchingapp3.model.Battle;

import java.util.ArrayList;

public class NoticeBListAdapter extends RecyclerView.Adapter<NoticeBListAdapter.ViewHolder> implements OnNoticeBClickListener {

    ArrayList<Battle> items = new ArrayList<Battle>();
    public int loginTeamId;
    OnNoticeBClickListener listener1, listener2;

    public NoticeBListAdapter(){}

    public NoticeBListAdapter(int loginTeamId){
        this.loginTeamId = loginTeamId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.item_battlelist, viewGroup, false);

        return new ViewHolder(itemView, listener1, listener2, loginTeamId);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeBListAdapter.ViewHolder viewHolder, int position) {
        Battle item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Battle item) {
        items.add(item);
    }

    public void setItems(ArrayList<Battle> items) {
        this.items = items;
    }

    public Battle getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, Battle item) {
        items.set(position, item);
    }

    public void setOnItemClickListener(OnNoticeBClickListener listener1, OnNoticeBClickListener listener2) {
        this.listener1 = listener1;
        this.listener2 = listener2;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener1 != null) {
            listener1.onItemClick(holder, view, position);
        }
        if (listener2 != null) {
            listener2.onItemClick(holder, view, position);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvReqName, tvResName, tvReqScore, tvResScore, tvDateReq, tvDateMatch, tvLocation, tvInfo;
        Button btnDetail, btnMatch;
        ImageView ivReqImage, ivResImage;
        View view;
        Battle item;
        int loginTeamId;

        public ViewHolder(final View itemView, final OnNoticeBClickListener listener1, final OnNoticeBClickListener listener2, final int loginTeamId) {
            super(itemView);
            this.view = itemView;
            this.loginTeamId = loginTeamId;

            tvReqName = itemView.findViewById(R.id.iBattle_tv_reqName);
            tvReqScore = itemView.findViewById(R.id.iBattle_tv_reqScore);
            tvResName = itemView.findViewById(R.id.iBattle_tv_resName);
            tvResScore = itemView.findViewById(R.id.iBattle_tv_resScore);
            tvDateReq = itemView.findViewById(R.id.iBattle_tv_dateReq);
            tvDateMatch = itemView.findViewById(R.id.iBattle_tv_dateMatch);
            tvLocation = itemView.findViewById(R.id.iBattle_tv_location);
            tvInfo = itemView.findViewById(R.id.iBattle_tv_info);

            ivReqImage = itemView.findViewById(R.id.iBattle_iv_reqImage);
            ivResImage = itemView.findViewById(R.id.iBattle_iv_resImage);
            btnDetail = itemView.findViewById(R.id.iBattle_btn_detail);
            btnMatch = itemView.findViewById(R.id.iBattle_btn_match);

            btnDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener1 != null) {
                        listener1.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });

            btnMatch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener2 != null) {
                        listener2.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });

        }

        public void setItem(Battle item) {
            this.item = item;
            tvReqName.setText(item.getRequestTeam().getName());
            tvReqScore.setText(item.getRequestTeam().getScore().getSummary());
            tvResName.setText(item.getResponseTeam().getName());
            tvResScore.setText(item.getResponseTeam().getScore().getSummary());
            tvDateReq.setText(item.getDateToString());
            tvDateMatch.setText(item.getMatchDate());
            tvLocation.setText(item.getLocation());
            tvInfo.setText(item.getInfo());

            if(loginTeamId == 0){
                btnDetail.setText("Home팀 보기");
                btnMatch.setText("Away팀 보기");
            }else if(loginTeamId == -1){
                btnDetail.setText("상대팀 보기");
                btnMatch.setVisibility(View.GONE);
            }else if(loginTeamId == item.getRequestTeam().getId()){
                btnMatch.setVisibility(View.GONE);
            }
            Glide.with(view).load(item.getRequestTeam().getUrlImage()).into(ivReqImage);
            Glide.with(view).load(item.getResponseTeam().getUrlImage()).into(ivResImage);
        }

    }
}
