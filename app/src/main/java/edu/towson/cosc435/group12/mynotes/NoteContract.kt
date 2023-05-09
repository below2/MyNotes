package edu.towson.cosc435.group12.mynotes

import android.provider.BaseColumns

object NoteContract {
    // Define table name and columns
    object NoteEntry : BaseColumns {
        const val TABLE_NAME = "note"
        const val COLUMN_NAME_NOTE_ID = "note_id"
        const val COLUMN_NAME_PROJECT_ID = "project_id"
        const val COLUMN_NAME_FRONT = "front"
        const val COLUMN_NAME_BACK = "back"
    }

    // Define SQL statements for creating and deleting table
    const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${NoteEntry.TABLE_NAME} (" +
                "${NoteEntry.COLUMN_NAME_NOTE_ID} TEXT PRIMARY KEY," +
                "${NoteEntry.COLUMN_NAME_PROJECT_ID} TEXT," +
                "${NoteEntry.COLUMN_NAME_FRONT} TEXT," +
                "${NoteEntry.COLUMN_NAME_BACK} TEXT)"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${NoteEntry.TABLE_NAME}"
}
