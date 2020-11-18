package com.project.matchingapp3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.matchingapp3.activity.LoginActivity;
import com.project.matchingapp3.activity.MyPageActivity;
import com.project.matchingapp3.activity.PartyListActivity;
import com.project.matchingapp3.activity.TeamCreateActivity;
import com.project.matchingapp3.activity.TeamDetailActivity;
import com.project.matchingapp3.adapter.ViewPagerAdapter;
import com.project.matchingapp3.fragment.HomeFragment1;
import com.project.matchingapp3.fragment.HomeFragment2;
import com.project.matchingapp3.fragment.HomeFragment3;
import com.project.matchingapp3.model.Battle;
import com.project.matchingapp3.model.Party;
import com.project.matchingapp3.model.User;
import com.project.matchingapp3.model.dto.NavDataDto;
import com.project.matchingapp3.task.RestAPITask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    ViewPager pager;
    Toolbar toolbar;
    DrawerLayout drawer;

    User loginUser;
    String jwtToken;
    ArrayList<Battle> myBattleList;
    ArrayList<Battle> allBattleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        jwtToken = intent.getStringExtra("jwtToken");

        Gson gson = new Gson();
        String[] result = new String[1];
        RestAPITask task = new RestAPITask(jwtToken);
        String[] result2 = new String[1];
        RestAPITask task2 = new RestAPITask(jwtToken);
        String[] result3 = new String[1];
        RestAPITask task3 = new RestAPITask(jwtToken);

        //loginUser 데이터 받기
        try {
            result = task.execute("user/app/loginUser").get();
            result2 = task2.execute("app/battleList").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e("test-Main액티비티", "loginUser데이터받음 : " + result[0]);
        Log.e("test-Main액티비티", "allBattle리스트 받음 : " + result2[0]);
        loginUser = gson.fromJson(result[0], User.class);
        allBattleList = gson.fromJson(result2[0], new TypeToken<ArrayList<Battle>>() {}.getType());

        if(loginUser.getTeams() != null) {
            try {
                result3 = task3.execute("app/battleList2/", Integer.toString(loginUser.getTeams().getId())).get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.e("test-Main액티비티", "myBattle리스트 받음 : " + result3[0]);
            myBattleList = gson.fromJson(result3[0], new TypeToken<ArrayList<Battle>>() {
            }.getType());
        }


        //툴바
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //드로어 레이아웃
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


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
        navName.setText(loginUser.getNickname());
        if(loginUser.getTeams() != null){
            navTName.setText(loginUser.getTeams().getName());
            navigationView.getMenu().getItem(1).setTitle("My Team");
        }


        //뷰 페이저
        pager = findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3); //미리 로딩해 놓을 아이템의 갯수, 스크롤 넘길시 빠른 조회가능

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        HomeFragment1 fragment1 = new HomeFragment1();
        adapter.addItem(fragment1);

        HomeFragment2 fragment2 = new HomeFragment2(myBattleList, loginUser, jwtToken);
        adapter.addItem(fragment2);

        HomeFragment3 fragment3 = new HomeFragment3(allBattleList, loginUser, jwtToken);
        adapter.addItem(fragment3);

        pager.setAdapter(adapter);


        //상단 탭 네비
        TabLayout tabs = findViewById(R.id.tab_layout);
        tabs.addTab(tabs.newTab().setText("용병구함"));
        tabs.addTab(tabs.newTab().setText("내 경기"));
        tabs.addTab(tabs.newTab().setText("모든 경기"));
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                String title = "";
                if(position == 0){
                    title = "용병구함";
                }else if(position == 1){
                    title = "내 경기";
                }else if(position == 2){
                    title = "모든 경기";
                }
                pager.setCurrentItem(position,true);   // true = 페이지 전환시 스무스
                toolbar.setTitle(title);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });


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
            case R.id.appbar_info:
                Intent intent2 = new Intent(getApplicationContext(), MyPageActivity.class);
                intent2.putExtra("jwtToken", jwtToken);
                intent2.putExtra("loginUser", loginUser);
                startActivity(intent2);
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
        toolbar.setTitle("홈");
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