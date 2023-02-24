package com.example.todo_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.todo_project.databinding.ActivityMainBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //회원가입 버튼
        val create: Button = binding.createBtn

        //로그인 버튼
        val login: Button = binding.loginBtn

        create.setOnClickListener() {
            val intent = Intent(this, Create::class.java)
            startActivity(intent)
        }

        login.setOnClickListener() {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        }
    }
