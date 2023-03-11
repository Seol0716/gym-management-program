package com.example.iotweight;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class mainview extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_view);
        Intent weightintent = getIntent();
        String weight = weightintent.getStringExtra("weight");
        TextView wei = (TextView) findViewById(R.id.wei_value);
        wei.setText("aa" + "Kg");
    }
}
