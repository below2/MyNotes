package edu.towson.cosc435.group12.mynotes

import java.util.*

interface INoteRepository {
    fun getNotes(): List<Note>
    fun getNote(noteId: String): Note
    fun getProjectNotes(projectId: String): List<Note>
    fun addNote(note : Note)
    fun removeNote(note: Note)
    fun editNote(noteId: String, front: String, back: String)
}