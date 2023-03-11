package com.example.iotweight;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class create_user extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.create_user);
        Button closebutton = (Button) findViewById(R.id.create_user_close);
        View.OnClickListener listener = new View.OnClickListener(){
            public void onClick(View v){
                Intent main = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main);
                finish();
            }
        };
        closebutton.setOnClickListener(listener);
        Button createbutton = (Button) findViewById(R.id.create_user_button);
        createbutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                EditText citext = (EditText) findViewById(R.id.create_user_id);
                EditText cptext = (EditText) findViewById(R.id.create_user_password);
                EditText cntext = (EditText) findViewById(R.id.create_user_nickname);
                EditText cktext = (EditText) findViewById(R.id.create_user_ki);
                EditText cwtext = (EditText) findViewById(R.id.create_user_weight);
                String ci = citext.getText().toString();
                String cp = cptext.getText().toString();
                String cn = cntext.getText().toString();
                String ck = cktext.getText().toString();
                String cw = cwtext.getText().toString();

                try{
                    if(ci.equals("")){
                        Toast.makeText(getApplicationContext(), "아이디를 입력하세요", Toast.LENGTH_LONG).show();
                    }
                    else if(cp.equals("")){
                        Toast.makeText(getApplicationContext(), "비밀번호를 입력하세요", Toast.LENGTH_LONG).show();
                    }
                    else if(cn.equals("")){
                        Toast.makeText(getApplicationContext(), "닉네임을 입력하세요", Toast.LENGTH_LONG).show();
                    }
                    else if(ck.equals("")){
                        Toast.makeText(getApplicationContext(), "키를 입력하세요", Toast.LENGTH_LONG).show();
                    }
                    else if(cw.equals("")){
                        Toast.makeText(getApplicationContext(), "몸무게를 입력하세요", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Registercreateuser ra = new Registercreateuser();
                        String result = ra.execute(ci, cp, cn, ck, cw).get();

                        if(result.equals("성공")){
                            Toast.makeText(getApplicationContext(), "회원가입 성공", Toast.LENGTH_LONG).show();
                            Intent mainintent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(mainintent);
                            finish();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "이미 존재하는 아이디입니다.", Toast.LENGTH_LONG).show();
                        }
                    }

                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
