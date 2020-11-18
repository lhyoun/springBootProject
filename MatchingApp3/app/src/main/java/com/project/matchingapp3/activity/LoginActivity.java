package com.project.matchingapp3.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.project.matchingapp3.MainActivity;
import com.project.matchingapp3.R;
import com.project.matchingapp3.model.User;
import com.project.matchingapp3.task.RestAPITask;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {

    Button btnJoin, btnLogin;
    TextView tvResult;
    EditText etId, etPw;
    CheckBox cbAuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences pref = getSharedPreferences("autoLogin",MODE_PRIVATE);
        String id = pref.getString("id","");
        String pw = pref.getString("pw", "");
        Log.d("noitem-세션id",id);
        Log.d("noitem-세션pw",pw);

        if(!id.equals("")&&!pw.equals("")){
            Log.d("noitem","자동 로그인 실행");
            Gson gson = new Gson();
            User user = new User();
            String[] result = new String[2];
            user.setLoginid(id);
            user.setPassword(pw);
            RestAPITask task = new RestAPITask();

            try {
                result = task.execute("login",gson.toJson(user)).get();
            } catch (ExecutionException e) {
                e.printStackTrace();
                Toast.makeText(LoginActivity.this, "서버통신오류", Toast.LENGTH_SHORT).show();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("jwtToken",result[1]);
            Toast.makeText(getApplicationContext(), "자동 로그인 되었습니다.",Toast.LENGTH_SHORT).show();
            startActivity(intent);

        }

        tvResult = findViewById(R.id.login_tv_result);
        etId = findViewById(R.id.login_et_id);
        etPw = findViewById(R.id.login_et_pw);
        btnJoin = findViewById(R.id.login_btn_join);
        btnLogin = findViewById(R.id.login_btn_login);
        cbAuto = findViewById(R.id.login_cb_auto);

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gson gson = new Gson();
                User user = new User();
                String[] result = new String[2];
                user.setLoginid(etId.getText().toString());
                user.setPassword(etPw.getText().toString());
                RestAPITask task = new RestAPITask();

                try {
                    result = task.execute("login", gson.toJson(user)).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this, "서버통신오류", Toast.LENGTH_SHORT).show();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("noitem-토큰값",result[1]);
                if (result[0].equals("ok")){
                    //자동로그인 체크시 토큰 저장
                    if(cbAuto.isChecked()){
                        Log.d("noitem-자동로그인체크","자동로그인 체크됨");
                        SharedPreferences pref = getSharedPreferences("autoLogin",MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("id", user.getLoginid());
                        editor.putString("pw", user.getPassword());
                        editor.commit();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("jwtToken",result[1]);
                        startActivity(intent);
                    }else {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("jwtToken",result[1]);
                        startActivity(intent);  //데이터 담고 가야됨.
                    }
                }else if(result[0].equals("비번x")){
                    tvResult.setText("비번 ㄴㄴ");
                    Toast.makeText(LoginActivity.this, "비번 ㄴㄴ", Toast.LENGTH_SHORT).show();
                }else{
                    tvResult.setText("아디 ㄴㄴ");
                    Toast.makeText(LoginActivity.this, "아디 ㄴㄴ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}