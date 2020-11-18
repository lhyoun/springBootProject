package com.project.matchingapp3.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.matchingapp3.MainActivity;
import com.project.matchingapp3.R;
import com.project.matchingapp3.TeamActivity;
import com.project.matchingapp3.UserActivity;
import com.project.matchingapp3.adapter.BattleUsersAdapter;
import com.project.matchingapp3.adapter.OnBattleUClickListener;
import com.project.matchingapp3.model.Battle;
import com.project.matchingapp3.model.Team;
import com.project.matchingapp3.model.TeamInfo;
import com.project.matchingapp3.model.User;
import com.project.matchingapp3.task.RestAPITask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class BattleActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    DrawerLayout drawer;
    RecyclerView recyclerView;
    BattleUsersAdapter adapter;

    User loginUser;
    String jwtToken;
    Team selectTeam;
    Battle selectBattle;

    int role;

    HashMap checkUsers = new HashMap();
    TeamInfo teamInfo = new TeamInfo();
    Gson gson = new Gson();
    Button btnChoice, btnAccept, btnMatch;
    EditText etLocation, etDate, etInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        Intent intent = getIntent();
        jwtToken = intent.getStringExtra("jwtToken");
        loginUser = (User)intent.getSerializableExtra("loginUser");
        selectTeam = (Team)intent.getSerializableExtra("selectTeam");
        selectBattle = (Battle)intent.getSerializableExtra("selectBattle");
        role = intent.getIntExtra("role", 0);

        btnChoice = findViewById(R.id.battle_btn_choice);
        btnMatch = findViewById(R.id.battle_btn_match);
        btnAccept = findViewById(R.id.battle_btn_accept);
        etLocation = findViewById(R.id.battle_et_location);
        etDate = findViewById(R.id.battle_et_date);
        etInfo = findViewById(R.id.battle_et_info);
        recyclerView = findViewById(R.id.recyclerView);
        //리사이클러뷰에 설정할 레이아웃 매니저 - 방향세로로 설정함.
        GridLayoutManager layoutManager = new GridLayoutManager(this,  2);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new BattleUsersAdapter();
        Log.e("test-Battle액티비티", "유저 데이터:" + loginUser);
        adapter.setItems((ArrayList) loginUser.getTeams().getUsers());
        Log.e("test-Battle액티비티", "유저 어댑터 관리수:" + adapter.getItemCount());

        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnBattleUClickListener() {
            @Override
            public void onItemClick(BattleUsersAdapter.ViewHolder holder, View view, int position) {
                CheckBox cb = view.findViewById(R.id.battle_item_ck_choice);
                if(cb.isChecked()){
                    Log.e("test-Battle액티비티", adapter.getItem(position)+"체크됨");
                    checkUsers.put(position, adapter.getItem(position));
                }else{
                    Log.e("test-Battle액티비티", "체크해제됨");
                    checkUsers.remove(position);
                }
                Log.e("test-Battle액티비티", "저장데이터 : " + checkUsers.size());
                Log.e("test-Battle액티비티", "저장데이터 : " + checkUsers.get(position));
                Log.e("test-Battle액티비티", "저장데이터 : " + checkUsers.toString());
            }
        });
        btnChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkUsers.size() != 11){
                    Snackbar.make(view, "선택 실패. 11명을 선택 해야합니다. 선택 수 = '" + checkUsers.size() + "'명", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                teamInfo.setUser1((User)checkUsers.get(0));
                teamInfo.setUser2((User)checkUsers.get(1));
                teamInfo.setUser3((User)checkUsers.get(2));
                teamInfo.setUser4((User)checkUsers.get(3));
                teamInfo.setUser5((User)checkUsers.get(4));
                teamInfo.setUser6((User)checkUsers.get(5));
                teamInfo.setUser7((User)checkUsers.get(6));
                teamInfo.setUser8((User)checkUsers.get(7));
                teamInfo.setUser9((User)checkUsers.get(8));
                teamInfo.setUser10((User)checkUsers.get(9));
                teamInfo.setUser11((User)checkUsers.get(10));

                String[] result = new String[1];
                RestAPITask task = new RestAPITask(jwtToken);
                try {
                    result = task.execute("user/teamInfo", gson.toJson(teamInfo)).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e("test-Battle액티비티", "선수선택 클릭 : " + result[0]);
                if(result[0].equals("ok")){
                    Snackbar.make(view, "팀 선택 완료.", Snackbar.LENGTH_SHORT).show();
                    btnChoice.setClickable(false);
                    btnChoice.setEnabled(false);
                }else {
                    Snackbar.make(view, "팀 선택 실패. " + result[0], Snackbar.LENGTH_SHORT).show();
                }
            }
        });
        if(role == 1) {
            etLocation.setText(selectBattle.getLocation());
            etDate.setText(selectBattle.getMatchDate());
            etInfo.setText(selectBattle.getInfo());
            btnMatch.setVisibility(View.GONE);
            btnAccept.setVisibility(View.VISIBLE);
            btnAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String[] result2 = new String[1];
                    RestAPITask task2 = new RestAPITask(jwtToken);
                    try {
                        result2 = task2.execute("user/matchAccept/", Integer.toString(selectBattle.getId())).get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.e("test-Battle액티비티", "매치 수락하기 클릭 : " + result2[0]);
                    if(result2[0].equals("ok")){
                        Snackbar.make(view, "매치 수락 완료.", Snackbar.LENGTH_SHORT).show();
                        //매치 리스트로 보내기
                        Intent intent = new Intent(getApplicationContext(), PartyListActivity.class);
                        intent.putExtra("jwtToken", jwtToken);
                        intent.putExtra("loginUser", loginUser);
                        startActivity(intent);
                    }else {
                        Snackbar.make(view, "매치 수락 실패. " + result2[0], Snackbar.LENGTH_SHORT).show();
                    }
                }
            });
        }
        btnMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etLocation.getText() == null || etDate.getText() == null || etInfo.getText() == null){
                    Snackbar.make(view, "빈 값을 다 채워주세요.", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(btnChoice.isClickable()){
                    Snackbar.make(view, "참여 선수를 먼저 선택하세요.", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                Battle battle = new Battle();
                battle.setLocation(etLocation.getText().toString());
                battle.setMatchDate(etDate.getText().toString());
                battle.setInfo(etInfo.getText().toString());

                String[] result3 = new String[1];
                RestAPITask task3 = new RestAPITask(jwtToken);
                try {
                    result3 = task3.execute("user/matchApply/", Integer.toString(selectTeam.getId()), gson.toJson(battle)).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e("test-Battle액티비티", "매치 신청하기 클릭 : " + result3[0]);
                if(result3[0].equals("ok")){
                    Snackbar.make(view, "매치 신청 완료.", Snackbar.LENGTH_SHORT).show();
                    //매치 리스트로 보내기
                    Intent intent = new Intent(getApplicationContext(), PartyListActivity.class);
                    intent.putExtra("jwtToken", jwtToken);
                    intent.putExtra("loginUser", loginUser);
                    startActivity(intent);
                }else {
                    Snackbar.make(view, "매치 신청 실패. " + result3[0], Snackbar.LENGTH_SHORT).show();
                }
            }
        });


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
        navName.setText(loginUser.getUsername()+"("+ loginUser.getNickname()+")");
        if(loginUser.getTeams() != null){
            navTName.setText(loginUser.getTeams().getName());
            navigationView.getMenu().getItem(1).setTitle("My Team");
        }


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
        toolbar.setTitle("경기 신청");
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