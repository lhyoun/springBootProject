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
import com.project.matchingapp3.model.Party;
import com.project.matchingapp3.model.User;

import java.util.ArrayList;

public class TeamDetailUsersAdapter extends RecyclerView.Adapter<TeamDetailUsersAdapter.ViewHolder> implements OnTDetailUClickListener {
    ArrayList<User> items = new ArrayList<User>();

    OnTDetailUClickListener listener1, listener2;

    @NonNull
    @Override
    public TeamDetailUsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.item_tdetail_users, viewGroup, false);

        return new TeamDetailUsersAdapter.ViewHolder(itemView, listener1, listener2);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamDetailUsersAdapter.ViewHolder viewHolder, int position) {
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

    public void setOnItemClickListener(OnTDetailUClickListener listener1, OnTDetailUClickListener listener2) {
        this.listener1 = listener1;
        this.listener2 = listener2;
    }

    @Override
    public void onItemClick(TeamDetailUsersAdapter.ViewHolder holder, View view, int position) {
        if (listener1 != null) {
            listener1.onItemClick(holder, view, position);
        }
        if (listener2 != null) {
            listener2.onItemClick(holder, view, position);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvAgePosition, tvLocation;
        Button btnInfo, btnBan;
        ImageView ivImage;
        View view;
        User item;

        public ViewHolder(final View itemView, final OnTDetailUClickListener listener1, final OnTDetailUClickListener listener2) {
            super(itemView);
            this.view = itemView;

            tvName = itemView.findViewById(R.id.tDetail_item_tv_name);
            tvAgePosition = itemView.findViewById(R.id.tDetail_item_tv_AgePosition);
            tvLocation = itemView.findViewById(R.id.tDetail_item_tv_location);
            ivImage = itemView.findViewById(R.id.tDetail_item_iv_image);
            btnInfo = itemView.findViewById(R.id.tDetail_item_btn_info);
            btnBan = itemView.findViewById(R.id.tDetail_item_btn_ban);

            btnInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener1 != null) {
                        listener1.onItemClick(TeamDetailUsersAdapter.ViewHolder.this, view, position);
                    }
                }
            });

            btnBan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener2 != null) {
                        listener2.onItemClick(TeamDetailUsersAdapter.ViewHolder.this, view, position);
                    }
                }
            });

        }

        public void setItem(User item) {
            this.item = item;
            tvName.setText(item.getNickname());
            tvAgePosition.setText("나이xx, " + item.getPosition());
            tvLocation.setText(item.getLocation());
            Glide.with(view).load(item.getUrlImage()).into(ivImage);
        }

    }
}
