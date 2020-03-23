package com.example.tasks

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val dbHelper = DbHelperWeekdays(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = dbHelper.writableDatabase
        val cursor = database.query(
            TABLE_TASKS, null, null,
            null, null, null, null
        )
        if (cursor.moveToFirst()) {

            editText.setText(cursor.getString(cursor.getColumnIndex(KEY_TASK1)))
            editText2.setText(cursor.getString(cursor.getColumnIndex(KEY_TASK2)))
            editText3.setText(cursor.getString(cursor.getColumnIndex(KEY_TASK3)))
            editText4.setText(cursor.getString(cursor.getColumnIndex(KEY_TASK4)))
            editText5.setText(cursor.getString(cursor.getColumnIndex(KEY_TASK5)))
            editText6.setText(cursor.getString(cursor.getColumnIndex(KEY_TASK6)))
            editText7.setText(cursor.getString(cursor.getColumnIndex(KEY_TASK7)))
            editText8.setText(cursor.getString(cursor.getColumnIndex(KEY_TASK8)))
            editText9.setText(cursor.getString(cursor.getColumnIndex(KEY_TASK9)))
            editText10.setText(cursor.getString(cursor.getColumnIndex(KEY_TASK10)))
            editText11.setText(cursor.getString(cursor.getColumnIndex(KEY_TASK11)))
        }
        cursor.close()


        btnWeek.setOnClickListener {
            val intent = Intent(this, Weekends::class.java)
            startActivity(intent)
        }
        btnClear.setOnClickListener {
            database.delete(TABLE_TASKS, null, null)
            editText.setText("")
            editText2.setText("")
            editText3.setText("")
            editText4.setText("")
            editText5.setText("")
            editText6.setText("")
            editText7.setText("")
            editText8.setText("")
            editText9.setText("")
            editText10.setText("")
            editText11.setText("")

            checkBox.isChecked = false
            checkBox2.isChecked = false
            checkBox3.isChecked = false
            checkBox4.isChecked = false
            checkBox5.isChecked = false
            checkBox6.isChecked = false
            checkBox7.isChecked = false
            checkBox8.isChecked = false
            checkBox9.isChecked = false
            checkBox10.isChecked = false
            checkBox11.isChecked = false
        }

        checkBox.isChecked = loadCheck("check")
        checkBox2.isChecked = loadCheck("check2")
        checkBox3.isChecked = loadCheck("check3")
        checkBox4.isChecked = loadCheck("check4")
        checkBox5.isChecked = loadCheck("check5")
        checkBox6.isChecked = loadCheck("check6")
        checkBox7.isChecked = loadCheck("check7")
        checkBox8.isChecked = loadCheck("check8")
        checkBox9.isChecked = loadCheck("check9")
        checkBox10.isChecked = loadCheck("check10")
        checkBox11.isChecked = loadCheck("check11")
    }

    override fun onBackPressed() {
        val intent = Intent()
        intent.action = Intent.ACTION_MAIN
        intent.addCategory(Intent.CATEGORY_HOME)
        startActivity(intent)
    }

    override fun onPause() {
        val database = dbHelper.writableDatabase
        val contentValues = ContentValues()

        val task1 = editText.text.toString()
        val task2 = editText2.text.toString()
        val task3 = editText3.text.toString()
        val task4 = editText4.text.toString()
        val task5 = editText5.text.toString()
        val task6 = editText6.text.toString()
        val task7 = editText7.text.toString()
        val task8 = editText8.text.toString()
        val task9 = editText9.text.toString()
        val task10 = editText10.text.toString()
        val task11 = editText11.text.toString()

        contentValues.put(KEY_TASK1, task1)
        contentValues.put(KEY_TASK2, task2)
        contentValues.put(KEY_TASK3, task3)
        contentValues.put(KEY_TASK4, task4)
        contentValues.put(KEY_TASK5, task5)
        contentValues.put(KEY_TASK6, task6)
        contentValues.put(KEY_TASK7, task7)
        contentValues.put(KEY_TASK8, task8)
        contentValues.put(KEY_TASK9, task9)
        contentValues.put(KEY_TASK10, task10)
        contentValues.put(KEY_TASK11, task11)

        database.insert(TABLE_TASKS, null, contentValues)
        database.update(TABLE_TASKS, contentValues, null, null)

        saveCheck(checkBox.isChecked, "check")
        saveCheck(checkBox2.isChecked, "check2")
        saveCheck(checkBox3.isChecked, "check3")
        saveCheck(checkBox4.isChecked, "check4")
        saveCheck(checkBox5.isChecked, "check5")
        saveCheck(checkBox6.isChecked, "check6")
        saveCheck(checkBox7.isChecked, "check7")
        saveCheck(checkBox8.isChecked, "check8")
        saveCheck(checkBox9.isChecked, "check9")
        saveCheck(checkBox10.isChecked, "check10")
        saveCheck(checkBox11.isChecked, "check11")
        super.onPause()
    }

    private fun saveCheck(isChecked: Boolean, key: String) {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean(key, isChecked)
        editor.apply()
    }

    private fun loadCheck(key: String):Boolean{
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        return sharedPref.getBoolean(key, false)
    }
}
