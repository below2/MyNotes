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

    override fun addNote(projectId : String, front : String, back : String) {
        _notes += Note(UUID.randomUUID().toString(), projectId, front, back)
    }

    override fun addSampleNote(projectId : String, sampleNote : SampleData) {
        val sampleFront: String = sampleNote.sampleFront
        val sampleBack: String = sampleNote.sampleBack

        _notes += Note(UUID.randomUUID().toString(), projectId, sampleFront, sampleBack)
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