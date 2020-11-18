package com.project.matchingapp3.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.ListActivity;
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
import com.google.android.material.snackbar.Snackbar;
import com.project.matchingapp3.MainActivity;
import com.project.matchingapp3.R;
import com.project.matchingapp3.TeamActivity;
import com.project.matchingapp3.UserActivity;
import com.project.matchingapp3.adapter.ViewPagerAdapter;
import com.project.matchingapp3.fragment.TeamDetailFragment1;
import com.project.matchingapp3.model.Team;
import com.project.matchingapp3.model.User;
import com.project.matchingapp3.task.RestAPITask;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class TeamDetailActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    ViewPager pager;
    Toolbar toolbar;
    DrawerLayout drawer;

    private String jwtToken;
    private User loginUser;
    private Team selectTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);

        Intent intent = getIntent();
        jwtToken = intent.getStringExtra("jwtToken");
        loginUser = (User)intent.getSerializableExtra("loginUser");
        selectTeam = (Team)intent.getSerializableExtra("selectTeam");
        Log.e("test-TeamDetail", "선택한팀 데이터 : " + selectTeam);

        //툴바
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //TextView 데이터 삽입
        TextView tvName = findViewById(R.id.tDetail_tv_tName);
        TextView tvLocation = findViewById(R.id.tDetail_tv_tLocation);
        TextView tvExplain = findViewById(R.id.tDetail_tv_tExplin);
        TextView tvCount = findViewById(R.id.tDetail_tv_tCount);
        TextView tvOwner = findViewById(R.id.tDetail_tv_tOwner);
        ImageView ivImage = findViewById(R.id.tDetail_iv_tImage);
        Button btnJoin = findViewById(R.id.tDetail_btnJoin);
        Button btnMatch = findViewById(R.id.tDetail_btnMatch);
        Button btnModify = findViewById(R.id.tDetail_btnInfo);

        tvName.setText(selectTeam.getName());
        tvLocation.setText(selectTeam.getLocation());
        tvExplain.setText(selectTeam.getExplaintation());
        tvCount.setText(selectTeam.getUsers().size() + " / 20");
        tvOwner.setText(selectTeam.getOwner().getNickname());
        Glide.with(this).load(selectTeam.getUrlImage()).into(ivImage);


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

        if(selectTeam.getOwner().getId() == loginUser.getId()){
            btnJoin.setVisibility(View.GONE);
            btnModify.setVisibility(View.VISIBLE);
        }else if(selectTeam.getId() != loginUser.getTeams().getId() &&
                loginUser.getId() == loginUser.getTeams().getOwner().getId()){
            btnJoin.setVisibility(View.GONE);
            btnMatch.setVisibility(View.VISIBLE);
        }
        btnMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BattleActivity.class);
                intent.putExtra("jwtToken", jwtToken);
                intent.putExtra("loginUser", loginUser);
                intent.putExtra("selectTeam", selectTeam);
                startActivity(intent);
            }
        });
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(loginUser.getTeams() != null){
                    Snackbar.make(view, "가입신청 실패.  이미 가입한 팀이 있습니다.", Snackbar.LENGTH_SHORT).show();
                    return ;
                }
                if(selectTeam.getUsers().size() >= 20){
                    Snackbar.make(view, "가입신청 실패.  해당 팀의 팀원이 가득찼습니다..", Snackbar.LENGTH_SHORT).show();
                    return ;
                }

                String[] result = new String[1];
                RestAPITask task = new RestAPITask(jwtToken);

                try {
                    result = task.execute("user/apply1/", Integer.toString(selectTeam.getId())).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(result[0].equals("ok")){
                    Snackbar.make(view, "가입신청 완료.  구단주가 수락하면 가입됩니다.", Snackbar.LENGTH_SHORT).show();
                }else{
                    Snackbar.make(view, "가입 신청 실패.  " + result[0], Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        //뷰 페이저
        pager = findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        TeamDetailFragment1 fragment1 = new TeamDetailFragment1((ArrayList)selectTeam.getUsers(), loginUser, jwtToken);
        adapter.addItem(fragment1);
        pager.setAdapter(adapter);
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
        toolbar.setTitle("팀 상세보기");
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