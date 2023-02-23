package com.example.todo_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Create : AppCompatActivity() {
    //lateinit 초기화 전 변수 설정
    private lateinit var create: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create)

        //초기화
        create = findViewById(R.id.create_btn) as Button

        create.setOnClickListener() {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}