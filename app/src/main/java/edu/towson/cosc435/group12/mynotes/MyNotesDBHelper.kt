package edu.towson.cosc435.group12.mynotes

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyNotesDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "MyNotes.db"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(NoteContract.SQL_CREATE_ENTRIES)
        db.execSQL(ProjectContract.SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(NoteContract.SQL_DELETE_ENTRIES)
        db.execSQL(ProjectContract.SQL_DELETE_ENTRIES)
        onCreate(db)
    }

}
