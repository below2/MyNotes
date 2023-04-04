package edu.towson.cosc435.group12.mynotes

interface INoteRepository {
    fun getNotes(): List<Note>
}