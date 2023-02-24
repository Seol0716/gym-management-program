package com.example.todo_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
class Create : AppCompatActivity() {
    //lateinit 초기화 전 변수 설정
    private lateinit var create: Button
    private lateinit var name: EditText
    private lateinit var pw: EditText
    private lateinit var id: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create)

        //초기화
        create = findViewById<Button>(R.id.create_btn)
        name = findViewById<EditText>(R.id.name)
        pw = findViewById<EditText>(R.id.password)
        id = findViewById<EditText>(R.id.id)


        create.setOnClickListener() {

            var db_name = name.text.toString()
            var db_id = id.text.toString()
            var db_pw = pw.text.toString()

            init(db_name, db_id, db_pw)

            Toast.makeText(applicationContext,"회원가입 완료", Toast.LENGTH_LONG).show()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    //
    private fun init(name: String, id: String, pw: String){

        //firebase 연결
        var database = FirebaseDatabase.getInstance();
        var myref  = database.getReference("data")

        var data = Data(name, id, pw)
        myref.child(data.user_id).setValue(data)
    }
    }
