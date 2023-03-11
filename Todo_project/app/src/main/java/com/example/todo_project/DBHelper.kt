/*
package com.example.todo_project

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(
    var context: Context?,

    //SQLITEOPENHelper 생성자(name,context,version)
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        const val DATABASE_NAME = "USER_DATA"
        const val DATABASE_VERSION = 1

        //Table 생성
        const val TABLE_NAME = "USER"
        const val UID = "UID"
        const val ID = "ID"
        const val PW = "PW"
        const val NAME = "NAME"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var sql: String = "CREATE TABLE IF NOT EXISTS " +
                "$TABLE_NAME ($UID integer primary key autoincrement, " +
                "$ID text, $PW text, $NAME text);"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        var sql = "DROP TABLE IF EXISTS $TABLE_NAME"

        db?.execSQL(sql)
        onCreate(db)
    }

    //allusers 리스트에 db에 저장되어 있는 모든 정보를 가져온다
    val allUsers:List<Data>
        @SuppressLint("Range")
        get() {
            val users = ArrayList<Data>()
            val selectQueryHandler = "SELECT * FROM $TABLE_NAME"
            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQueryHandler,null)
            if(cursor.moveToFirst()){
                do{
                    val user = Data()
                    user.id = cursor.getString(cursor.getColumnIndex(ID))
                    user.pw = cursor.getString(cursor.getColumnIndex(PW))
                    user.name = cursor.getString(cursor.getColumnIndex(NAME))

                    users.add(user)
                }while(cursor.moveToNext())
            }
            db.close()
            return users
        }

    fun addUser(user: Data){
        val db = this.writableDatabase
        val value = ContentValues()
        value.put(ID, user.id)
        value.put(PW, user.pw)
        value.put(NAME, user.name)

        db.insert(TABLE_NAME, null, value)
        db.close()

}
}
*/

