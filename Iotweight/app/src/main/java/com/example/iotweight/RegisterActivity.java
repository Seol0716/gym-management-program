package com.example.iotweight;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegisterActivity extends AsyncTask<String, Void, String>{
    String sendmsg, receivemsg;

    protected String doInBackground(String... strings){
        try{
            String str;
            URL url = new URL("http://118.223.70.219:8088/index.jsp");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
            sendmsg = "id="+strings[0] + "&pwd=" + strings[1];
            osw.write(sendmsg);
            osw.flush();
            if(conn.getResponseCode() == conn.HTTP_OK){
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while((str = reader.readLine()) != null){
                    buffer.append(str);
                }
                receivemsg = buffer.toString();
            }
            else{
                Log.i("통신 결과", conn.getResponseCode() + "에러");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return receivemsg;
    }
}
