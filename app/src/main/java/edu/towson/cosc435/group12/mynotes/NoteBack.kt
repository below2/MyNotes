package edu.towson.cosc435.group12.mynotes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun NoteBack(
    navController: NavController,
    noteId: String,
    notes: List<Note>
) {
    Card(
        shape = RoundedCornerShape(5.dp),
        elevation = 16.dp,
        modifier = Modifier
            .padding(start=16.dp, end=16.dp, top=5.dp, bottom=5.dp)
            .fillMaxWidth()
    ) {
        val note = getNote(noteId, notes)
        Text(note.back)
    }
}

fun getNote(noteId: String, notes: List<Note>): Note {
    for(note in notes) {
        if(noteId == note.noteId) {
            return note
        }
    }
    return Note("", "", "", "")
}