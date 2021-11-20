package com.hunseong.sqlite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n", "Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Fragment

        val insertBtn = findViewById<Button>(R.id.insert_btn)
        val selectBtn = findViewById<Button>(R.id.select_btn)
        val updateBtn = findViewById<Button>(R.id.update_btn)
        val deleteBtn = findViewById<Button>(R.id.delete_btn)
        val tv = findViewById<TextView>(R.id.tv)

        insertBtn.setOnClickListener {
            val helper = DBHelper(this)
            val db = helper.writableDatabase

//            val sql = "insert into TestTable (textData, intData, floatData, dateData) values (?, ?, ?, ? )"
//
//            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
//            val date = sdf.format(Date())
//
//            val arg1 = arrayOf("문자열1", "100", "11.11", date)
//            val arg2 = arrayOf("문자열2", "200", "22.22", date)
//
//            db.execSQL(sql, arg1)
//            db.execSQL(sql, arg2)

            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = sdf.format(Date())

            val cv1 = ContentValues()
            cv1.put("textData", "문자열1")
            cv1.put("intData", 1)
            cv1.put("floatData",11.11)
            cv1.put("dateData", date)

            val cv2 = ContentValues()
            cv2.put("textData", "문자열2")
            cv2.put("intData", 2)
            cv2.put("floatData",22.22)
            cv2.put("dateData", date)

            db.insert("TestTable", null, cv1)
            db.insert("TestTable", null, cv2)

            db.close()

            tv.text = "저장 완료"
        }

        selectBtn.setOnClickListener {
            val helper = DBHelper(this)
            val db: SQLiteDatabase = helper.writableDatabase

//            val sql = "select * from TestTable"
//
//            val c: Cursor = db.rawQuery(sql, null)


            // param1 : 테이블 이름
            // param2 : 가져올 column 이름의 문자열 배열. 전체를 가져올 땐 null
            // param3 : 조건절 ("a=? and b=?") 없으면 null
            // param4 : 조건절에 해당하는 value(?)의 문자열 배열. 없으면 null
            // param5 : 정렬 기준
            // param6 : Group by
            // param7 : having
            val c = db.query("TestTable", null, null, null, null, null, null)

            tv.text = ""

            while (c.moveToNext()) {
                val index = c.getColumnIndex("idx")
                val textDataIdx = c.getColumnIndex("textData")
                val intDataIdx = c.getColumnIndex("intData")
                val floatDataIdx = c.getColumnIndex("floatData")
                val dateDataIdx = c.getColumnIndex("dateData")

                val idx = c.getInt(index)
                val textData = c.getString(textDataIdx)
                val intData = c.getInt(intDataIdx)
                val floatData = c.getDouble(floatDataIdx)
                val dateData = c.getString(dateDataIdx)

                tv.append("idx : $idx \n")
                tv.append("textData : $textData \n")
                tv.append("intData : $intData \n")
                tv.append("floatData : $floatData \n")
                tv.append("dateData : $dateData \n\n")
            }

            db.close()
        }

        updateBtn.setOnClickListener {
            val helper = DBHelper(this)
            val db = helper.writableDatabase

//            val sql = "update TestTable set textData=? where idx=?"
//            val args = arrayOf("문자열3", "1")
//            db.execSQL(sql, args)

            val cv = ContentValues()
            cv.put("textData", "문자열3")

            val where = "idx=?"
            val args = arrayOf("1")

            db.update("TestTable", cv, where, args)



            tv.text = "수정 완료"
        }

        deleteBtn.setOnClickListener {
            val helper = DBHelper(this)
            val db = helper.writableDatabase

//            val sql = "delete from TestTable where idx=?"
//            val args = arrayOf("1")
//            db.execSQL(sql, args)

            val where = "idx=?"
            val args = arrayOf("1")

            db.delete("TestTable", where, args)

            db.close()

            tv.text = "삭제 완료"
        }
    }
}