package com.project.matchingapp3.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.matchingapp3.R;
import com.project.matchingapp3.adapter.OnUserItemClickListener;
import com.project.matchingapp3.adapter.UserListAdapter;
import com.project.matchingapp3.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserFragment2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_fragment2, container, false);
    }
}