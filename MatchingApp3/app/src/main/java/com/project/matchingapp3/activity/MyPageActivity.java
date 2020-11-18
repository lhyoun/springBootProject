package com.project.matchingapp3.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.matchingapp3.MainActivity;
import com.project.matchingapp3.R;
import com.project.matchingapp3.TeamActivity;
import com.project.matchingapp3.UserActivity;
import com.project.matchingapp3.model.Team;
import com.project.matchingapp3.model.User;
import com.project.matchingapp3.model.dto.NavDataDto;
import com.project.matchingapp3.task.RestAPITask;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MyPageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Toolbar toolbar;
    DrawerLayout drawer;
    BottomNavigationView bottomNavigationView;

    User loginUser;
    String jwtToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        Intent intent = getIntent();
        jwtToken = intent.getStringExtra("jwtToken");
        loginUser = (User)intent.getSerializableExtra("loginUser");


        //툴바
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //user데이터 view출력
        TextView tvName = findViewById(R.id.mypage_et_nickname);
        TextView tvLocation = findViewById(R.id.mypage_et_location);
        TextView tvPosition = findViewById(R.id.mypage_et_position);
        TextView tvEmail = findViewById(R.id.mypage_et_email);
        TextView tvPhone = findViewById(R.id.mypage_et_phone);
        ImageView ivImage = findViewById(R.id.mypage_iv_image);

        tvName.setText(loginUser.getNickname());
        tvLocation.setText(loginUser.getLocation());
        tvPosition.setText(loginUser.getPosition());
        tvEmail.setText(loginUser.getEmail());
        tvPhone.setText(loginUser.getPhone());
        Glide.with(this).load(loginUser.getUrlImage()).into(ivImage);

        //드로어 레이아웃
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        //하단 탭 네비
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.tab1:
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("jwtToken", jwtToken);
                        startActivity(intent);
                        return true;
                    case R.id.tab2:
                        Intent intent2 = new Intent(getApplicationContext(), TeamActivity.class);
                        intent2.putExtra("jwtToken", jwtToken);
                        intent2.putExtra("loginUser", loginUser);
                        startActivity(intent2);
                        return true;
                    case R.id.tab3:
                        Intent intent3 = new Intent(getApplicationContext(), UserActivity.class);
                        intent3.putExtra("jwtToken", jwtToken);
                        intent3.putExtra("loginUser", loginUser);
                        startActivity(intent3);
                        return true;
                }
                return false;
            }
        });


        //네비게이션 뷰
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //네비뷰의 로그아웃 버튼
        View header = navigationView.getHeaderView(0);
        Button btnLogout = header.findViewById(R.id.navHeader_btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences("autoLogin", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.remove("id");
                editor.remove("pw");
                editor.commit();

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        //네비뷰 헤더의 사용자 정보
        //이미지
        if(loginUser.getImage() != null) {
            ImageView navImage = header.findViewById(R.id.navHeader_iv_image);
            Glide.with(this).load(loginUser.getUrlImage()).into(navImage);
        }
        //텍스트
        TextView navName = header.findViewById(R.id.navHeader_tv_username);
        TextView navTName = header.findViewById(R.id.navHeader_tv_tName);
        navName.setText(loginUser.getUsername()+"("+ loginUser.getNickname()+")");
        if(loginUser.getTeams() != null){
            navTName.setText(loginUser.getTeams().getName());
            navigationView.getMenu().getItem(1).setTitle("My Team");
        }
    }

    //앱바 메뉴의 아이템 선택시 -
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int curId = item.getItemId();
        switch (curId) {
            case R.id.appbar_search:
                Intent intent = new Intent(getApplicationContext(), PartyListActivity.class);
                intent.putExtra("jwtToken", jwtToken);
                intent.putExtra("loginUser", loginUser);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //앱바 메뉴 인플레이션
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        toolbar.setTitle("마이페이지");
        return true;
    }

    //네비게이션 메뉴의 아이템 선택시 - 인텐트 액티비티 이동, 페이지 이동 구현
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_menu1) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("jwtToken", jwtToken);
            startActivity(intent);
        } else if (id == R.id.nav_menu2) {
            if(loginUser.getTeams() != null){
                Intent intent = new Intent(getApplicationContext(), TeamDetailActivity.class);
                intent.putExtra("jwtToken", jwtToken);
                intent.putExtra("loginUser", loginUser);
                intent.putExtra("selectTeam", loginUser.getTeams());
                startActivity(intent);
            }else{
                Intent intent = new Intent(getApplicationContext(), TeamCreateActivity.class);
                intent.putExtra("jwtToken", jwtToken);
                intent.putExtra("loginUser", loginUser);
                startActivity(intent);
            }
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //뒤로가기 때 호출 - 네비창 닫기
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}