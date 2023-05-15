package edu.towson.cosc435.group12.mynotes

import java.util.*

class NoteRepository : INoteRepository {
    private var _notes = listOf<Note>()

    init { }

    override fun getNotes(): List<Note> {
        return _notes
    }

    override fun getNote(noteId: String): Note {
        for (note in _notes) {
            if (note.noteId == noteId) {
                return note
            }
        }

        return Note("", "", "", "")
    }

    override fun getProjectNotes(projectId: String): List<Note> {
        var projectNotes = listOf<Note>()
        for (note in _notes) {
            if (note.projectId == projectId) {
                projectNotes += note
            }
        }
        return projectNotes
    }

    override fun addNote(note: Note) {
        _notes += note
    }

    override fun removeNote(note: Note) {
        _notes = _notes.filterNot { it == note}
    }

    override fun editNote(noteId: String, front: String, back: String) {
        for (note in _notes) {
            if (note.noteId == noteId) {
                note.front = front
                note.back = back
            }
        }
    }
}