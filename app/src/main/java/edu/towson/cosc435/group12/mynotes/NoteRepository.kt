package edu.towson.cosc435.group12.mynotes

import java.util.*

class NoteRepository : INoteRepository {
    private var _notes = listOf<Note>()

    init {
        _notes = (0..20).map { i ->
            Note(UUID.randomUUID().toString(), "Front $i", "Back $i")
        }
    }

    override fun getNotes(): List<Note> {
        return _notes
    }
}