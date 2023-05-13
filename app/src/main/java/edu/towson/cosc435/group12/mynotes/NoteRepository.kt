package edu.towson.cosc435.group12.mynotes

import java.util.*


//class NoteRepository(context: Context) : INoteRepository {
//
//    private val dbHelper = MyNotesDBHelper(context)
//
//    override fun getNotes(): List<Note> {
//        val db = dbHelper.readableDatabase
//
//        val projection = arrayOf(
//            NoteContract.NoteEntry.COLUMN_NAME_NOTE_ID,
//            NoteContract.NoteEntry.COLUMN_NAME_PROJECT_ID,
//            NoteContract
// update to this so on so on


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