package com.hunseong.new_project

import android.annotation.SuppressLint
import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv = findViewById<TextView>(R.id.tv)
        val selectBtn = findViewById<Button>(R.id.select_btn)
        val insertBtn = findViewById<Button>(R.id.insert_btn)
        val updateBtn = findViewById<Button>(R.id.update_btn)
        val deleteBtn = findViewById<Button>(R.id.delete_btn)

        val uri = Uri.parse("content://com.hunseong.dbprovider")

        selectBtn.setOnClickListener {

            val c = contentResolver.query(uri, null, null, null, null)

            tv.text = ""

            if (c != null) {
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
            }
        }

        insertBtn.setOnClickListener {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = sdf.format(Date())

            val cv1 = ContentValues()
            cv1.put("textData", "문자열3")
            cv1.put("intData", 3)
            cv1.put("floatData",33.33)
            cv1.put("dateData", date)
            contentResolver.insert(uri, cv1)

            val cv2 = ContentValues()
            cv2.put("textData", "문자열4")
            cv2.put("intData", 4)
            cv2.put("floatData",44.44)
            cv2.put("dateData", date)
            contentResolver.insert(uri, cv2)

            tv.text = "저장 완료"
        }

        updateBtn.setOnClickListener {
            val cv = ContentValues()
            cv.put("textData", "idx3 문자열 수정")

            val where = "idx=?"
            val args = arrayOf("3")

            contentResolver.update(uri, cv, where, args)

            tv.text = "수정 완료"
        }

        deleteBtn.setOnClickListener {
            val where = "idx=?"
            val args = arrayOf("3")

            contentResolver.delete(uri, where, args)

            tv.text = "삭제 완료"
        }
    }
}