package com.example.todo_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todo_project.databinding.CreateBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
class Create : AppCompatActivity() {
    //lateinit 초기화 전 변수 설정
    private lateinit var create: Button
    private lateinit var email: EditText
    private lateinit var pw: EditText
    private lateinit var auth : FirebaseAuth


    private lateinit var binding: CreateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //뷰 바인딩
        binding = CreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //초기화
        create = binding.createBtn
        email = binding.email
        pw= binding.password


        create.setOnClickListener() {


            var email = email.text.toString()
            var pw = pw.text.toString()


            //firebase 연결
            auth = Firebase.auth


            init(email, pw)

        }

    }

    //
    private fun init(email: String, pw: String){

        if(email.isNullOrEmpty() || pw.isNullOrEmpty()) {
            Toast.makeText(this, "이메일 혹인 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        else {
            auth.createUserWithEmailAndPassword(email,pw).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "회원가입 완료", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "이메일 형식인지 확인 또는 비밀번호 6자리이상 입력해주세요!", Toast.LENGTH_SHORT).show()
                }
            }
        }
        }
        }
