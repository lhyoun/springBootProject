package com.project.matchingapp3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.matchingapp3.R;
import com.project.matchingapp3.model.Team;
import com.project.matchingapp3.model.User;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder>
        implements OnUserItemClickListener  {
    ArrayList<User> items = new ArrayList<User>();

    OnUserItemClickListener listener;

    @NonNull
    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.userlist_item, viewGroup, false);

        return new UserListAdapter.ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListAdapter.ViewHolder viewHolder, int position) {
        User item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(User item) {
        items.add(item);
    }

    public void setItems(ArrayList<User> items) {
        this.items = items;
    }

    public User getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, User item) {
        items.set(position, item);
    }

    public void setOnItemClickListener(OnUserItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemClick(UserListAdapter.ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvLocation, tvPosition, tvTeamName;
        ImageView ivImage;
        View view;

        public ViewHolder(View itemView, final OnUserItemClickListener listener) {
            super(itemView);
            this.view = itemView;

            tvName = itemView.findViewById(R.id.iUser_tv_Name);
            tvLocation = itemView.findViewById(R.id.iUser_tv_location);
            tvPosition = itemView.findViewById(R.id.iUser_tv_position);
            tvTeamName = itemView.findViewById(R.id.iUser_tv_team);
            ivImage = itemView.findViewById(R.id.iUser_tv_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null) {
                        listener.onItemClick(UserListAdapter.ViewHolder.this, view, position);
                    }
                }
            });
        }

        public void setItem(User item) {
            tvName.setText(item.getNickname());
            tvLocation.setText(item.getLocation());
            if(item.getTeams() != null){
                tvTeamName.setText(item.getTeams().getName());
            }
            tvPosition.setText(item.getPosition());
            Glide.with(view).load(item.getUrlImage()).into(ivImage);
        }

    }
}
