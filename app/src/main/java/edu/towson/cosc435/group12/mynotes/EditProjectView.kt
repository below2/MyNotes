package edu.towson.cosc435.group12.mynotes

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(project.projectName)
                        IconButton(
                            onClick = {
                                navController.navigate(Routes.EditProjectName.createRoute(projectId))
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit",
                                tint = Color.Gray
                            )
                        }
                    }
                }
            )
        }
    ) {
        Box(modifier = Modifier.background(MaterialTheme.colors.background)) {
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
                                    NoteRow(navController, note)
                                }
                            }
                        )
                    }
                )
            }
        }
    }
}