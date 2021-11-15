package com.hunseong.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(context: Context) : SQLiteOpenHelper(context, "Test.db", null, 3) {

    override fun onCreate(p0: SQLiteDatabase?) {
        Log.d("msg", "onCreate: ")

        val sql = "create table TestTable (" +
                "idx integer primary key autoincrement, " +
                "textData text not null, " +
                "intData integer not null, " +
                "floatData real not null, " +
                "dateData date not null" +
                ")"

        p0?.execSQL(sql)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.d("msg", "oldVersion : $oldVersion / newVersion : $newVersion")
    }

}