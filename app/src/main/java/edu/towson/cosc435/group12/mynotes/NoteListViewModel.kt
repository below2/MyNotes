package edu.towson.cosc435.group12.mynotes

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class NoteListViewModel : ViewModel() {
    private val _notes: MutableState<List<Note>> = mutableStateOf(listOf())
    var notes: State<List<Note>> = _notes

    private val _repository: INoteRepository = NoteRepository()

    init {
        _notes.value = _repository.getNotes()
    }

    fun getNotes(): List<Note> {
        return _repository.getNotes()
    }

    fun getNote(noteId: String): Note {
        return _repository.getNote(noteId)
    }

    fun getProjectNotes(projectId: String): List<Note> {
        return _repository.getProjectNotes(projectId)
    }

    fun addNote(projectId: String, front: String, back: String) {
        _repository.addNote(projectId, front, back)
        _notes.value = _repository.getNotes()
    }

    fun addSampleNote(projectId: String, sampleNote: SampleData) {
        _repository.addSampleNote(projectId, sampleNote)
        _notes.value = _repository.getNotes()
    }

    fun removeNote(note: Note) {
        _repository.removeNote(note)
        _notes.value = _repository.getNotes()
    }

    fun editNote(noteId: String, front: String, back: String) {
        _repository.editNote(noteId, front, back)
        _notes.value = _repository.getNotes()
    }
}