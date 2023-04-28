package edu.towson.cosc435.group12.mynotes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun NoteListView(
    navController: NavController,
    projectId: String,
    notevm: NoteListViewModel
) {
    val notes by notevm.notes
    LazyColumn {
        items(
            items = notes,
            key = { note -> note.noteId }
        ) { note ->
            if (note.projectId == projectId) {
                NoteRow(
                    note,
                    notevm
                ) { navController.navigate(Routes.NoteBack.createRoute(note.noteId)) }
            }
        }
    }
}