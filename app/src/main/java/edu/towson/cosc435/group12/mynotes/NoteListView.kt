package edu.towson.cosc435.group12.mynotes

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun NoteListView(
    navController: NavController,
    projectId: String,
    notes: List<Note>
) {
    LazyColumn {
        items(notes.size) { index ->
            if (notes[index].projectId == projectId) {
                NoteRow(notes[index]) { navController.navigate(Routes.NoteBack.createRoute(notes[index].noteId)) }
            }
        }
    }
}