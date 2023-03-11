package com.example.todo_project

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todo_project.databinding.LoginBinding

class Login : AppCompatActivity() {
    private lateinit var login: Button
    private lateinit var binding: LoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        login = binding.backBtn


        login.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "뒤로 이동", Toast.LENGTH_LONG).show()

            var intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        })
     }
}