package edu.towson.cosc435.group12.mynotes.ui.theme

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract.CommonDataKinds.Note
import edu.towson.cosc435.group12.mynotes.NoteDB

class DatabaseHandler(context: Context): SQLiteOpenHelper(context, dbName, null, databaseVersion) {
    companion object{
        private val databaseVersion = 1
        private val dbName = "NoteDatabase"
        private val dbTable = "NoteTable"
        private val noteID = "ID"
        private val key_Project = "project"
        private val key_Note = "note"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_PROJECT_TABLE = ("CREATE TABLE" + dbTable + "(" + noteID + "PROJECT PRIMARY KEY" + key_Project + "PROJECT" + key_Note + "NOTE_NAME" +")")
        db?.execSQL(CREATE_PROJECT_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
       db!!.execSQL("DROP TABLE IF EXISTS" + dbTable)
        onCreate(db)
    }

    fun addNotefromProject(note: NoteDB):Long{
        val db = this.writableDatabase
        val contentVal = ContentValues()
        contentVal.put(noteID,note.noteID)
        contentVal.put(key_Note,note.noteName)
        contentVal.put(key_Project,note.projectName)
        //Insert
        val success = db.insert(dbTable,null,contentVal)
        db.close() //close line for db
        return success
    }

    fun viewNotes():List<NoteDB> {
        val empList: ArrayList<NoteDB> = ArrayList<NoteDB>()
        val selectQuery = "SELECT *FROM $dbTable"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var noteId: Int
        var noteName: String
        var noteProject: String
        if (cursor.moveToFirst()) {
            do {
                noteId = cursor.getInt(cursor.getColumnIndexOrThrow("ID"))
                noteName = cursor.getString(cursor.getColumnIndexOrThrow("note"))
                noteProject = cursor.getString(cursor.getColumnIndexOrThrow("project"))
                val note = NoteDB(noteID = noteId, noteName = noteName, projectName = noteProject)
                empList.add(note)
            } while (cursor.moveToNext())
        }
        return empList
    }

    fun updateNotes(note: NoteDB): Int {
        val db = this.writableDatabase
        val contentVal = ContentValues()
        contentVal.put(noteID,note.noteID)
        contentVal.put(key_Note,note.noteName)
        contentVal.put(key_Project,note.projectName)
        val success = db.update(dbTable, contentVal, "id=" + note.noteID,null)
        db.close()
        return success
    }

    fun deleteNotes(note: NoteDB): Int {
        val db = this.writableDatabase
        val contentVal = ContentValues()
        contentVal.put(noteID,note.noteID)
        contentVal.put(key_Note,note.noteName)
        contentVal.put(key_Project,note.projectName)
        val success = db.delete(dbTable,"id=" + note.noteID,null)
        db.close()
        return success
    }


}