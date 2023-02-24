package com.example.todo_project

class Data(name: String, id: String, pw: String){
    var user_name : String
    var user_id : String
    var user_pw : String

    init{
        this.user_name = name
        this.user_id = id
        this.user_pw = pw
    }
}