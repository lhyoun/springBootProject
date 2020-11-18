package com.project.matchingapp3.task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageTask extends AsyncTask<String, Integer, Bitmap>{

    private String serverUrl = "http://"+IP.ip+":8000/image/"; // 연결할 서버주소
    private Bitmap bmImg;

    @Override
    protected Bitmap doInBackground(String... strings) {
        try{
            URL myFileUrl = new URL(serverUrl + strings[0]);
            HttpURLConnection conn = (HttpURLConnection)myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();

            InputStream is = conn.getInputStream();

            bmImg = BitmapFactory.decodeStream(is);

        }catch(IOException e){
            e.printStackTrace();
        }
        return bmImg;
    }

}
