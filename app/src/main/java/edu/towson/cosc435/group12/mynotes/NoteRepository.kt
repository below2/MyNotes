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
                Note(UUID.randomUUID().toString(), "0", "Front $i", "Back $i", false)
            } else if (i in 6..10) {
                Note(UUID.randomUUID().toString(), "1", "Front $i", "Back $i", false)
            } else if (i in 11..14) {
                Note(UUID.randomUUID().toString(), "2", "Front $i", "Back $i", false)
            } else if (i in 15..17) {
                Note(UUID.randomUUID().toString(), "3", "Front $i", "Back $i", false)
            } else {
                Note(UUID.randomUUID().toString(), "4", "Front $i", "Back $i", false)
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

        return Note("", "", "", "", false)
    }

    override fun addNote(projectId : String, front : String, back : String, isSelected : Boolean) {
        _notes += Note(UUID.randomUUID().toString(), projectId, front, back, isSelected)
    }

    override fun readyDelete(selectedNote: Note) {
        for (note in _notes) {
            note.isSelected = false
        }
        selectedNote.isSelected = true
    }

    override fun selectNote(selectedNote: Note): Boolean {
        selectedNote.isSelected = !selectedNote.isSelected
        return selectedNote.isSelected
    }
}