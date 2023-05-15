package edu.towson.cosc435.group12.mynotes

import androidx.room.*

@Dao
interface NoteDAO {
    @Query("SELECT * FROM notestable")
    fun getAllNotes(): List<Note>

    @Query("SELECT * FROM notestable WHERE noteid = :noteId")
    fun getNoteById(noteId: String): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Query("DELETE FROM notestable")
    fun deleteAllNotes()
}