package com.example.iotweight;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONObject;




public class MainActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button login = (Button) findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText idtext = (EditText) findViewById(R.id.idText);
                EditText pwdtext = (EditText) findViewById(R.id.pwText);
                String id = idtext.getText().toString();
                String pwd = pwdtext.getText().toString();

                try{
                    RegisterActivity ra = new RegisterActivity();
                    String result = ra.execute(id, pwd).get();
                    JSONObject json = new JSONObject(result);
                    JSONArray jArr = json.getJSONArray("List");
                    json = jArr.getJSONObject(0);
                    String sc = json.getString("result");
                    json = jArr.getJSONObject(1);
                    String height = json.getString("height");
                    if(sc.equals("성공")){
                        json = jArr.getJSONObject(1);
                        //String weight = json.getString("weight");
                        Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_LONG).show();
                        Intent myintent = new Intent(getApplicationContext(), test.class);
                        myintent.putExtra("id", id);
                        myintent.putExtra("height", height);
                        startActivity(myintent);
                        finish();


                    }
                    else{
                        Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_LONG).show();
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });


        Button createuser = (Button) findViewById(R.id.create_user);
        createuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cuintent = new Intent(getApplicationContext(), create_user.class);
                startActivity(cuintent);
            }
        });


    }
}
