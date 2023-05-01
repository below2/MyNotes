package edu.towson.cosc435.group12.mynotes

import java.util.*

class NoteRepository : INoteRepository {
    private var _notes = listOf<Note>()

    init {
//        _notes = (0..20).map { i ->
//            Note(UUID.randomUUID().toString(), "", "Front $i", "Back $i")
//        }

        // This artificially inits notes into different projects, will be reverted to above when adding note feature is implemented
        _notes = (0..20).map { i ->
            if (i in 0..5) {
                Note(UUID.randomUUID().toString(), "0", "Front $i", "Back $i")
            } else if (i in 6..10) {
                Note(UUID.randomUUID().toString(), "1", "Front $i", "Back $i")
            } else if (i in 11..14) {
                Note(UUID.randomUUID().toString(), "2", "Front $i", "Back $i")
            } else if (i in 15..17) {
                Note(UUID.randomUUID().toString(), "3", "Front $i", "Back $i")
            } else {
                Note(UUID.randomUUID().toString(), "4", "Front $i", "Back $i")
            }
        }
    }

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

    override fun addNote(projectId : String, front : String, back : String) {
        _notes += Note(UUID.randomUUID().toString(), projectId, front, back)
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