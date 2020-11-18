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
import com.project.matchingapp3.fragment.TeamFragment1;
import com.project.matchingapp3.model.Team;
import com.project.matchingapp3.model.User;

import java.util.ArrayList;

public class TeamListAdapter extends RecyclerView.Adapter<TeamListAdapter.ViewHolder>
        implements OnTeamItemClickListener  {
    ArrayList<Team> items = new ArrayList<Team>();

    OnTeamItemClickListener listener;

    @NonNull
    @Override
    public TeamListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.teamlist_item, viewGroup, false);

        return new TeamListAdapter.ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamListAdapter.ViewHolder viewHolder, int position) {
        Team item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Team item) {
        items.add(item);
    }

    public void setItems(ArrayList<Team> items) {
        this.items = items;
    }

    public Team getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, Team item) {
        items.set(position, item);
    }

    public void setOnItemClickListener(OnTeamItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemClick(TeamListAdapter.ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvLocation, tvCount, tvScore;
        ImageView ivImage;
        View view;

        public ViewHolder(View itemView, final OnTeamItemClickListener listener) {
            super(itemView);
            this.view = itemView;

            tvName = itemView.findViewById(R.id.iTeam_tv_tName);
            tvLocation = itemView.findViewById(R.id.iTeam_tv_tLocation);
            tvCount = itemView.findViewById(R.id.iTeam_tv_tCount);
            tvScore = itemView.findViewById(R.id.iTeam_tv_tScore);
            ivImage = itemView.findViewById(R.id.iTeam_iv_tImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null) {
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });
        }

        public void setItem(Team item) {
            tvName.setText(item.getName());
            tvLocation.setText(item.getLocation());
            tvCount.setText(item.getUsers().size()+" / 20");
            tvScore.setText(item.getScore().getSummary());
            Glide.with(view).load(item.getUrlImage()).into(ivImage);
        }

    }
}
