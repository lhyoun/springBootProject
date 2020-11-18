package com.project.matchingapp3.task;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class RestAPITask extends AsyncTask<String, Object, String[]> {

    private String serverUrl = "http://" + IP.ip + ":8000/"; // 연결할 서버주소
    private String reqUrl = "";
    private String method = "POST";
    private String contentType = "application/json; charset=utf-8";
    private String authorization = "";
    private String reqData = "noData";
    private String resData = "noData";

    public RestAPITask(){}

    public RestAPITask(String authorization){
        this.authorization = authorization;
    }

    @Override
    protected String[] doInBackground(String... json) {
        if(json[0].equals("login")){
            reqUrl = json[0];
            serverUrl += reqUrl;
            method = "POST";
            reqData = json[1];
        }else if(json[0].equals("join")){
            reqUrl = json[0];
            serverUrl += reqUrl;
            reqData = json[1];
        }else if(json[0].equals("user/app/loginUser")){
            reqUrl = json[0];
            serverUrl += reqUrl;
        }else if(json[0].equals("user/create")){
            reqUrl = json[0];
            serverUrl += reqUrl;
            reqData = json[1];
        }else if(json[0].equals("app/teamList")){
            reqUrl = json[0];
            serverUrl += reqUrl;
        }else if(json[0].equals("app/userList")){
            reqUrl = json[0];
            serverUrl += reqUrl;
        }else if(json[0].equals("user/apply1/")){
            reqUrl = json[0];
            serverUrl += reqUrl + json[1];
        }else if(json[0].equals("user/apply2/")){
            reqUrl = json[0];
            serverUrl += reqUrl + json[1];
        }else if(json[0].equals("user/app/teamPartyList")){
            reqUrl = json[0];
            serverUrl += reqUrl;
        }else if(json[0].equals("user/app/userPartyList")){
            reqUrl = json[0];
            serverUrl += reqUrl;
        }else if(json[0].equals("Acknowledgment/")){
            reqUrl = json[0];
            serverUrl += reqUrl + json[1];
            method = "PUT";
        }else if(json[0].equals("user/teamInfo")){
            reqUrl = json[0];
            serverUrl += reqUrl;
            reqData = json[1];
        }else if(json[0].equals("user/matchApply/")){
            reqUrl = json[0];
            serverUrl += reqUrl + json[1];
            reqData = json[2];
        }else if(json[0].equals("app/battleList/")){
            reqUrl = json[0];
            serverUrl += reqUrl + json[1];
        }else if(json[0].equals("app/battleList")){
            reqUrl = json[0];
            serverUrl += reqUrl;
        }else if(json[0].equals("app/battleList2/")){
            reqUrl = json[0];
            serverUrl += reqUrl + json[1];
        }else if(json[0].equals("rank")){
            reqUrl = json[0];
            serverUrl += reqUrl;
            method = "PUT";
        }else if(json[0].equals("user/matchAccept/")){
            reqUrl = json[0];
            serverUrl += reqUrl + json[1];
            method = "PUT";
        }

        try {
            String str = "";
            URL url = new URL(serverUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            Log.d("noitem","토큰대가리심음 : "+authorization);
            conn.setRequestProperty("Authorization", authorization);   //토큰
            conn.setRequestProperty("Content-Type", contentType);
            conn.setRequestMethod(method);
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");

            if(!reqData.equals("noData")){
                osw.write(reqData);
            }

            osw.flush();
            osw.close();

            if(conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();

                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                resData = buffer.toString();
                //login요청일 때만 map에 헤더정보 넣기.
                if(reqUrl.equals("login")){
                    Map m = conn.getHeaderFields();
                    if(m.containsKey("Authorization")) {
                        Collection c =(Collection)m.get("Authorization");
                        for(Iterator i = c.iterator(); i.hasNext(); ) {
                            authorization = (String)i.next();
                        }
                        Log.d("noitem-받은header 토큰값", authorization);
                    }
                    Log.d("test-RestAPITsk", "reqData : " + reqData);
                    Log.d("test-RestAPITsk", "resData : " + resData);
                    return new String[]{resData, authorization};
                }
            } else {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();

                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }

                resData = buffer.toString();

                Log.d("응답 코드", conn.getResponseCode()+"에러");    //응답코드받기
                Log.d("응답 메시지", conn.getResponseMessage());    //응답메시지
                Log.d("noitem", "응답코드가 ok가 아님");
            }
        } catch (MalformedURLException e) {
            Log.d("noitem","MalformedURLException");
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("noitem","IOException");
            e.printStackTrace();
        }
        Log.d("test-RestAPITsk", "req body data : " + reqData);
        Log.d("test-RestAPITsk", "res body data : " + resData);

        return new String[]{resData};
    }

}
