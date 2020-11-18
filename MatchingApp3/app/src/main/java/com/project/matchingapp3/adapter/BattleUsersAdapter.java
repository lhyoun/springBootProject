package com.project.matchingapp3.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.matchingapp3.R;
import com.project.matchingapp3.model.User;

import java.util.ArrayList;

public class BattleUsersAdapter extends RecyclerView.Adapter<BattleUsersAdapter.ViewHolder> implements OnBattleUClickListener {
    ArrayList<User> items = new ArrayList<User>();

    OnBattleUClickListener listener1;

    @NonNull
    @Override
    public BattleUsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.item_battle_users, viewGroup, false);

        return new BattleUsersAdapter.ViewHolder(itemView, listener1);
    }

    @Override
    public void onBindViewHolder(@NonNull BattleUsersAdapter.ViewHolder viewHolder, int position) {
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

    public void setOnItemClickListener(OnBattleUClickListener listener1) {
        this.listener1 = listener1;
    }

    @Override
    public void onItemClick(BattleUsersAdapter.ViewHolder holder, View view, int position) {
        if (listener1 != null) {
            listener1.onItemClick(holder, view, position);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvAgePosition, tvLocation;
        CheckBox cbChoice;
        ImageView ivImage;
        View view;
        User item;

        public ViewHolder(final View itemView, final OnBattleUClickListener listener1) {
            super(itemView);
            this.view = itemView;

            tvName = itemView.findViewById(R.id.battle_item_tv_name);
            tvAgePosition = itemView.findViewById(R.id.battle_item_tv_AgePosition);
            tvLocation = itemView.findViewById(R.id.battle_item_tv_location);
            ivImage = itemView.findViewById(R.id.battle_item_iv_image);
            cbChoice = itemView.findViewById(R.id.battle_item_ck_choice);


            cbChoice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener1 != null) {
                        listener1.onItemClick(BattleUsersAdapter.ViewHolder.this, view, position);
                    }
                }
            });

        }

        public void setItem(User item) {
            this.item = item;
            tvName.setText(item.getNickname());
            tvAgePosition.setText(item.getPosition());
            tvLocation.setText(item.getLocation());
            Glide.with(view).load(item.getUrlImage()).into(ivImage);
        }

    }
}
