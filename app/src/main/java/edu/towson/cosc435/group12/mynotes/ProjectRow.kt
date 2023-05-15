package edu.towson.cosc435.group12.mynotes

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun ProjectRow(
    navController: NavController,
    projectvm: ProjectListViewModel,
    project: Project,
    notevm: NoteListViewModel,
    onClick: () -> Unit = {},
    onLongClick: () -> Unit = {}
) {
    val projectDatabase = ProjectDatabase.getInstance(LocalContext.current)
    val noteDatabase = NoteDatabase.getInstance(LocalContext.current)
    var expandedMenu by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Card(
            shape = RoundedCornerShape(5.dp),
            elevation = 16.dp,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 5.dp, bottom = 5.dp)
                .fillMaxWidth()
                .height(50.dp)
                .clickable(onClick = onClick)
                .combinedClickable(
                    onClick = onClick,
                    onLongClick = onLongClick
                )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = project.projectName,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Box {
                    IconButton(
                        onClick = { expandedMenu = true },
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "More",
                            tint = Color.Gray
                        )
                    }

                    DropdownMenu(
                        expanded = expandedMenu,
                        onDismissRequest = { expandedMenu = false }
                    ) {
                        DropdownMenuItem(
                            onClick = {
                                expandedMenu = false
                                navController.navigate(Routes.EditProject.createRoute(project.projectId))
                            }
                        ) {
                            Text("Edit")
                        }
                        Divider()
                        DropdownMenuItem(
                            onClick = {
                                expandedMenu = false
                                showDialog = true
//                                projectvm.removeProject(project, notevm)
                            }
                        ) {
                            Text(
                                text = "Delete",
                                color = MaterialTheme.colors.primaryVariant
                            )
                        }
                    }

                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = { showDialog = false },
                            title = {
                                Text("Confirm Project Deletion")
                            },
                            text = {
                                Text("Are you sure you want to delete the project: ${project.projectName}?")
                            },
                            confirmButton = {
                                Button(
                                    onClick = {
                                        showDialog = false
                                        val projectDao = projectDatabase.projectDao()
                                        val noteDao = noteDatabase.noteDao()
                                        projectvm.removeProject(project, notevm)
                                        projectvm.removeProjectDB(projectDao, noteDao, project, notevm)
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = MaterialTheme.colors.primaryVariant
                                    )
                                ) {
                                    Text(text = "Delete")
                                }
                            },
                            dismissButton = {
                                Button(
                                    onClick = { showDialog = false },
                                ) {
                                    Text("Cancel")
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}