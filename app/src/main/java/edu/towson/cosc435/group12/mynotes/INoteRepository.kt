package edu.towson.cosc435.group12.mynotes

interface INoteRepository {
    fun getNotes(): List<Note>
    fun getNote(noteId: String): Note
    fun getProjectNotes(projectId: String): List<Note>
    fun addNote(projectId : String, front : String, back : String)
    fun removeNote(note: Note)
    fun editNote(noteId: String, front: String, back: String)
    fun setStudied(noteId: String, isStudied: Boolean)
}