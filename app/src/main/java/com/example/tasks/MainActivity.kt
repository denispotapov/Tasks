package com.example.tasks

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val dbHelper = DbHelperWeekdays(this)
    private lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = dbHelper.writableDatabase

        initEditTexts()
        initCheckBoxes()
        setButtonClearListener()

        btnWeek.setOnClickListener {
            startActivity(Intent(this, WeekendsActivity::class.java))
        }
    }

    private fun initEditTexts() {
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
    }

    private fun initCheckBoxes() {
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

    private fun loadCheck(key: String): Boolean =
        getPreferences(Context.MODE_PRIVATE).getBoolean(key, false)

    private fun setButtonClearListener() {
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
    }

    override fun onPause() {
        saveDataToDatabase()
        saveDataToSharedPrefs()
        super.onPause()
    }

    private fun saveDataToDatabase() {
        val contentValues = ContentValues().apply {
            put(KEY_TASK1, editText.text.toString())
            put(KEY_TASK2, editText2.text.toString())
            put(KEY_TASK3, editText3.text.toString())
            put(KEY_TASK4, editText4.text.toString())
            put(KEY_TASK5, editText5.text.toString())
            put(KEY_TASK6, editText6.text.toString())
            put(KEY_TASK7, editText7.text.toString())
            put(KEY_TASK8, editText8.text.toString())
            put(KEY_TASK9, editText9.text.toString())
            put(KEY_TASK10, editText10.text.toString())
            put(KEY_TASK11, editText11.text.toString())
        }

        database.apply {
            insert(TABLE_TASKS, null, contentValues)
            update(TABLE_TASKS, contentValues, null, null)
        }
    }

    private fun saveDataToSharedPrefs() {
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
    }

    private fun saveCheck(isChecked: Boolean, key: String) {
        getPreferences(Context.MODE_PRIVATE).edit().apply {
            putBoolean(key, isChecked)
            apply()
        }
    }

    override fun onBackPressed() {
        val intent = Intent().apply {
            action = Intent.ACTION_MAIN
            addCategory(Intent.CATEGORY_HOME)
        }
        startActivity(intent)
    }
}
