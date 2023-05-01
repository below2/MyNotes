package edu.towson.cosc435.group12.mynotes

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun EditProjectView(
    navController: NavHostController,
    projectId: String,
    projectvm: ProjectListViewModel,
    notevm: NoteListViewModel
) {
    val noteListState = notevm.notes
    val project = projectvm.getProject(projectId)

    Text(project.projectName)

    LazyColumn {
        items(
            items = noteListState.value,
            key = { note -> note.noteId },
            itemContent = { note ->
                val currentNote by rememberUpdatedState(note)
                val dismissState = rememberDismissState(
                    confirmStateChange = {
                        if (it == DismissValue.DismissedToStart) {
                            notevm.removeNote(currentNote)
                            true
                        } else false
                    }
                )

                if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                    notevm.removeNote(note)
                }

                SwipeToDismiss(
                    state = dismissState,
                    modifier = Modifier
                        .padding(vertical = 1.dp)
                        .animateItemPlacement(),
                    directions = setOf(
                        DismissDirection.EndToStart
                    ),
                    background = {
                        SwipeBackground(dismissState)
                    },
                    dismissContent = {
                        if (note.projectId == projectId) {
                            NoteRow(
                                navController,
                                note,
                            ) { navController.navigate(Routes.NoteBack.createRoute(note.noteId)) }
                        }
                    }
                )
            }
        )
    }
}