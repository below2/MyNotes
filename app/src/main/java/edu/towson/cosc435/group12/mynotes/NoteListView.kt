package edu.towson.cosc435.group12.mynotes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun NoteListView(
    navController: NavController,
    projectId: String,
    notes: List<Note>
) {
    LazyColumn {
        items(notes) { note ->
            if (note.projectId == projectId) {
                NoteRow(note) { navController.navigate(Routes.NoteBack.createRoute(note.noteId)) }
            }
        }
    }
}