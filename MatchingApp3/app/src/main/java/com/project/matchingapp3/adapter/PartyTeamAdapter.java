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

import java.util.ArrayList;

public class PartyTeamAdapter extends RecyclerView.Adapter<PartyTeamAdapter.ViewHolder> implements OnPartyTClickListener {

    ArrayList<Party> items = new ArrayList<Party>();

    OnPartyTClickListener listener1, listener2;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.item_partylist, viewGroup, false);

        return new ViewHolder(itemView, listener1, listener2);
    }

    @Override
    public void onBindViewHolder(@NonNull PartyTeamAdapter.ViewHolder viewHolder, int position) {
        Party item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Party item) {
        items.add(item);
    }

    public void setItems(ArrayList<Party> items) {
        this.items = items;
    }

    public Party getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, Party item) {
        items.set(position, item);
    }

    public void setOnItemClickListener(OnPartyTClickListener listener1, OnPartyTClickListener listener2) {
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
        TextView tvName, tvScore, tvLocation;
        Button btnInfo, btnAccept;
        ImageView ivImage;
        View view;
        Party item;

        public ViewHolder(final View itemView, final OnPartyTClickListener listener1, final OnPartyTClickListener listener2) {
            super(itemView);
            this.view = itemView;

            tvName = itemView.findViewById(R.id.party_tv_name);
            tvScore = itemView.findViewById(R.id.party_tv_scoreOrAgePosition);
            tvLocation = itemView.findViewById(R.id.party_tv_location);
            ivImage = itemView.findViewById(R.id.party_iv_image);
            btnInfo = itemView.findViewById(R.id.party_btn_info);
            btnAccept = itemView.findViewById(R.id.party_btn_accept);

            btnInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener1 != null) {
                        listener1.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });

            btnAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener2 != null) {
                        listener2.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });

        }

        public void setItem(Party item) {
            this.item = item;
            tvName.setText(item.getTeam().getName());
            tvLocation.setText(item.getTeam().getLocation());
            //점수tvScore.setText(item.getTeam().getScore());
            Glide.with(view).load(item.getTeam().getUrlImage()).into(ivImage);
        }

    }
}
