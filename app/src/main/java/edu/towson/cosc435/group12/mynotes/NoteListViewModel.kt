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

    fun addNote(projectId: String, front: String, back: String, isSelected: Boolean) {
        _repository.addNote(projectId, front, back, isSelected)
        _notes.value = _repository.getNotes()
    }

    fun readyDelete(selectedNote: Note) {
        _repository.readyDelete(selectedNote)
        _notes.value = _repository.getNotes()
    }

    fun selectNote(selectedNote: Note): Boolean {
        _repository.selectNote(selectedNote)
        _notes.value = _repository.getNotes()
        return selectedNote.isSelected
    }
}