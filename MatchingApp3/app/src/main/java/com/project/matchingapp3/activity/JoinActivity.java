package com.project.matchingapp3.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.project.matchingapp3.R;
import com.project.matchingapp3.model.User;
import com.project.matchingapp3.task.ImageUpload;
import com.project.matchingapp3.task.RestAPITask;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class JoinActivity extends AppCompatActivity {

    EditText etId, etPw, etPw2, etName, etNickname, etPhone, etEmail, etLocation;
    Button btnUploadImg, btnSelectImg, btnJoin;
    RadioGroup rgPosition;
    ImageView ivSelectImg;

    String[] permission_list = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private final int REQ_CODE_SELECT_IMAGE = 100;
    private String img_path = new String();
    private Bitmap image_bitmap_copy = null;
    private Bitmap image_bitmap = null;
    private String imageName = null;

    private String pathUserImg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        //권한 확인
        checkPermission();

        etId = findViewById(R.id.join_et_id);
        etPw = findViewById(R.id.join_et_pw);
        etPw2 = findViewById(R.id.join_et_pw2);
        etName = findViewById(R.id.join_et_name);
        etNickname = findViewById(R.id.join_et_nickname);
        etPhone = findViewById(R.id.join_et_phone);
        etEmail = findViewById(R.id.join_et_email);
        etLocation = findViewById(R.id.join_et_location);
        btnUploadImg = findViewById(R.id.join_btn_imageUpload);
        btnSelectImg = findViewById(R.id.join_btn_imageSelect);
        btnJoin = findViewById(R.id.join_btn_join);
        rgPosition = findViewById(R.id.join_rg_position);
        ivSelectImg = findViewById(R.id.join_iv_image);

        btnSelectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent를 통해 이미지를 선택
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
            }
        });

        btnUploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageUpload imgUpload = new ImageUpload();
                try {
                    pathUserImg = imgUpload.execute("imgUpload", img_path).get();
                    Toast.makeText(getApplicationContext(), "이미지 전송 성공", Toast.LENGTH_SHORT).show();
                    Log.d("noitem-이미지업로드", "Success");
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //빈칸있는지 비밀번호같은지 아디,닉넴중복검사 등 해야함.
                if(pathUserImg == null){
                    Toast.makeText(getBaseContext(), "이미지를 먼저 등록해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Gson gson = new Gson();
                String[] result = new String[1];
                RestAPITask task = new RestAPITask();
                User user = new User();

                RadioButton rbPosition = findViewById(rgPosition.getCheckedRadioButtonId());
                user.setLoginid(etId.getText().toString());
                user.setPassword(etPw.getText().toString());
                user.setUsername(etName.getText().toString());
                user.setNickname(etNickname.getText().toString());
                user.setPhone(etPhone.getText().toString());
                user.setEmail(etEmail.getText().toString());
                user.setLocation(etLocation.getText().toString());
                user.setImage(pathUserImg);
                user.setPosition(rbPosition.getText().toString());
                Log.e("noitem-라디오값", user.getPosition());

                try {
                    result = task.execute("join", gson.toJson(user)).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("noitem",result[0]);
                if(result[0].equals("ok")){
                    Toast.makeText(getBaseContext(), "회원가입 성공.", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(getBaseContext(), "회원가입 실패.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //권한 확인
    private void checkPermission() {
        //현재 안드로이드 버전이 6.0미만이면 메서드를 종료한다.
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return;

        for(String permission : permission_list){
            //권한 허용 여부를 확인한다.
            int chk = checkCallingOrSelfPermission(permission);

            if(chk == PackageManager.PERMISSION_DENIED){
                //권한 허용을여부를 확인하는 창을 띄운다
                requestPermissions(permission_list,0);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==0)
        {
            for(int i=0; i<grantResults.length; i++)
            {
                //허용됬다면
                if(grantResults[i]==PackageManager.PERMISSION_GRANTED){
                }
                else {
                    Toast.makeText(getApplicationContext(),"앱권한을 설정 해주세요.",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        }
    }

    //사용자가 선택한 이미지의 정보를 받아옴
    public String getImagePathToUri(Uri data) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();

        //이미지의 경로 값
        String imgPath = cursor.getString(column_index);
        Log.d("noitem", imgPath);

        //이미지의 이름 값
        String imgName = imgPath.substring(imgPath.lastIndexOf("/") + 1);
        Log.d("noitem - 이미지 이름", imgName);


        this.imageName = imgName;

        return imgPath;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("noitem - ", "인텐트로부터 받은 이미지 URI :" + data);
        //Toast.makeText(getBaseContext(), "resultCode : " + data, Toast.LENGTH_SHORT).show();
        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    img_path = getImagePathToUri(data.getData()); //이미지의 URI를 얻어 경로값으로 반환.
                    Log.d("noitem - 이미지 경로", img_path);
                    //이미지를 비트맵형식으로 반환
                    image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());

                    //사용자 단말기의 width , height 값 반환
                    int reWidth = (int) (getWindowManager().getDefaultDisplay().getWidth());
                    int reHeight = (int) (getWindowManager().getDefaultDisplay().getHeight());

                    //image_bitmap 으로 받아온 이미지의 사이즈를 임의적으로 조절함. width: 400 , height: 300
                    image_bitmap_copy = Bitmap.createScaledBitmap(image_bitmap, 400, 300, true);
                    ivSelectImg.setImageBitmap(image_bitmap_copy);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}