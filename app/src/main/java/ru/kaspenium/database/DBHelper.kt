package ru.kaspenium.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {



    companion object{
        private val DATABASE_NAME = "database"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "students"
        val ID_COL = "id"
        val NAME_COL = "name"
        val WIDTH_COL = "width"
        val HEIGHT_COL = "height"
        val AGE_COL = "age"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE $TABLE_NAME ($ID_COL INTEGER PRIMARY KEY, $NAME_COL TEXT, $WIDTH_COL INT, $HEIGHT_COL INT, $AGE_COL INT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addName(name : String, width : Int, height : Int, age : Int ){
        val values = ContentValues()

        values.put(NAME_COL, name)
        values.put(WIDTH_COL, width)
        values.put(HEIGHT_COL, age)
        values.put(AGE_COL, age)

        val db = this.writableDatabase

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getName(): Cursor? {

        val db = this.readableDatabase

        return db.rawQuery("SELECT * FROM $TABLE_NAME ORDER BY $AGE_COL" , null)

    }
}