package com.example.tasks

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

const val DATABASE_VERSION = 1
const val DATABASE_NAME = "tasksDb"
const val TABLE_TASKS = "tasks"

const val KEY_ID = "_id"
const val KEY_TASK1 = "task1"
const val KEY_TASK2 = "task2"
const val KEY_TASK3 = "task3"
const val KEY_TASK4 = "task4"
const val KEY_TASK5 = "task5"
const val KEY_TASK6 = "task6"
const val KEY_TASK7 = "task7"
const val KEY_TASK8 = "task8"
const val KEY_TASK9 = "task9"
const val KEY_TASK10 = "task10"
const val KEY_TASK11 = "task11"


class DbHelperWeekdays(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $TABLE_TASKS ($KEY_ID INTEGER PRIMARY KEY, $KEY_TASK1 TEXT, $KEY_TASK2 TEXT, $KEY_TASK3 TEXT, $KEY_TASK4 TEXT, $KEY_TASK5 TEXT, $KEY_TASK6 TEXT, $KEY_TASK7 TEXT, $KEY_TASK8 TEXT, $KEY_TASK9 TEXT, $KEY_TASK10 TEXT, $KEY_TASK11 TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_TASKS")
        onCreate(db)
    }
}