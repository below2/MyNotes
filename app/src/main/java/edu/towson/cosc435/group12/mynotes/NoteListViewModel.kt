package edu.towson.cosc435.group12.mynotes

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class NoteListViewModel : ViewModel() {
    private val _notes: MutableState<List<Note>> = mutableStateOf(listOf())
    val notes: State<List<Note>> = _notes

    private val _repository: INoteRepository = NoteRepository()

    init {
        _notes.value = _repository.getNotes()
    }
}