package edu.towson.cosc435.group12.mynotes

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    fun addNote(note: Note) {
        _repository.addNote(note)
        _notes.value = _repository.getNotes()
    }

    fun addNoteDB(noteDao: NoteDAO, note: Note) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                noteDao.insertNote(note)
            }
        }
    }

    fun removeNote(note: Note) {
        _repository.removeNote(note)
        _notes.value = _repository.getNotes()
    }

    fun removeNoteDB(noteDao: NoteDAO, note: Note) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                noteDao.deleteNote(note)
            }
        }
    }

    fun editNote(noteId: String, front: String, back: String) {
        _repository.editNote(noteId, front, back)
        _notes.value = _repository.getNotes()
    }

    fun editNoteDB(
        noteId: String,
        projectId: String,
        noteDao: NoteDAO,
        front: String,
        back: String
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                noteDao.updateNote(Note(noteId, projectId, front, back))
            }
        }
    }
}