package edu.towson.cosc435.group12.mynotes

interface INoteRepository {
    fun getNotes(): List<Note>
    fun addNote(projectId : String, front : String, back : String)
}