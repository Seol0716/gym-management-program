package com.example.iotweight;

import android.app.Activity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class test extends AppCompatActivity {
    Fragment mainfragment;
    Fragment calfragment;
    Fragment graphfragment;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        Intent sintent = getIntent();
        String id = sintent.getStringExtra("id");
        String height = sintent.getStringExtra("height");

        mainfragment = new mainfrag();
        Bundle bundle = new Bundle(2);
        bundle.putString("id", id);
        bundle.putString("height", height);
        mainfragment.setArguments(bundle);

        calfragment = new calfrag();
        calfragment.setArguments(bundle);

        graphfragment = new graphfrag();
        graphfragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.home_ly, mainfragment).commit();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.first_tab:
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_ly, mainfragment).commit();
                        return true;
                    case R.id.second_tab:
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_ly, calfragment).commit();
                        return true;
                    case R.id.third_tab:
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_ly, graphfragment).commit();
                        return true;
                }
                return false;
            }
        });
    }
}
