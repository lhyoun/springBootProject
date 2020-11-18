package com.project.matchingapp3.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.matchingapp3.R;
import com.project.matchingapp3.model.Score;

import java.util.ArrayList;

public class ScoreTeamAdapter extends RecyclerView.Adapter<ScoreTeamAdapter.ViewHolder>
        implements OnScoreTClickListener  {
    ArrayList<Score> items = new ArrayList<Score>();

    OnScoreTClickListener listener;

    @NonNull
    @Override
    public ScoreTeamAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.item_score, viewGroup, false);

        return new ScoreTeamAdapter.ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreTeamAdapter.ViewHolder viewHolder, int position) {
        Score item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Score item) {
        items.add(item);
    }

    public void setItems(ArrayList<Score> items) {
        this.items = items;
    }

    public Score getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, Score item) {
        items.set(position, item);
    }

    public void setOnItemClickListener(OnScoreTClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemClick(ScoreTeamAdapter.ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvRank, tvName, tvTotal, tvWin, tvDraw, tvLose;
        ImageView ivImage;
        View view;

        public ViewHolder(View itemView, final OnScoreTClickListener listener) {
            super(itemView);
            this.view = itemView;

            tvRank = itemView.findViewById(R.id.iScore_tv_rank);
            tvName = itemView.findViewById(R.id.iScore_tv_name);
            tvTotal = itemView.findViewById(R.id.iScore_tv_total);
            tvWin = itemView.findViewById(R.id.iScore_tv_win);
            tvDraw = itemView.findViewById(R.id.iScore_tv_draw);
            tvLose = itemView.findViewById(R.id.iScore_tv_lose);
            ivImage = itemView.findViewById(R.id.iScore_iv_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null) {
                        listener.onItemClick(ScoreTeamAdapter.ViewHolder.this, view, position);
                    }
                }
            });
        }

        public void setItem(Score item) {
            tvRank.setText(Integer.toString(item.getRank()));
            tvName.setText(item.getTeam().getName());
            tvTotal.setText(Integer.toString(item.getTotal()));
            tvWin.setText(Integer.toString(item.getWin()));
            tvDraw.setText(Integer.toString(item.getDraw()));
            tvLose.setText(Integer.toString(item.getLose()));
            Glide.with(view).load(item.getTeam().getUrlImage()).into(ivImage);
        }

    }
}
