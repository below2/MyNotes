package edu.towson.cosc435.group12.mynotes

import androidx.compose.animation.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun ProjectListView(
    navController: NavController,
    projectvm: ProjectListViewModel,
    notevm: NoteListViewModel
) {
    val projects = projectvm.projects
    var showSampleRequest by remember { mutableStateOf(projectvm.getSampleRequestState()) }
    var showHelperText by remember { mutableStateOf(true) }

    if (projects.value.isNotEmpty()) {
        LazyColumn {
            items(
                items = projects.value,
                key = { project -> project.projectId },
                itemContent = { project ->
                    ProjectRow(
                        navController = navController,
                        projectvm = projectvm,
                        project = project,
                        notevm = notevm,
                        onClick = { navController.navigate(Routes.NotesFront.createRoute(project.projectId)) },
                        onLongClick = {
                            navController.navigate(
                                Routes.EditProject.createRoute(
                                    project.projectId
                                )
                            )
                        }
                    )
                }
            )
        }
        if (projects.value.size == 1 && notevm.getNotes().isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Create a note ",
                        style = MaterialTheme.typography.subtitle1,
                        color = MaterialTheme.colors.secondaryVariant
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "",
                        tint = MaterialTheme.colors.secondaryVariant
                    )
                }
            }
        }
    } else {
        AnimatedVisibility(
            visible = showSampleRequest,
            enter = fadeIn() + slideInHorizontally(initialOffsetX = { it }),
            exit = fadeOut() + slideOutHorizontally(targetOffsetX = { it })
        ) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .animateContentSize(),
            ) {
                Card(
                    shape = RoundedCornerShape(5.dp),
                    elevation = 16.dp,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 5.dp, bottom = 5.dp)
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Add a sample project?",
                            modifier = Modifier.padding(start = 16.dp)
                        )
                        Box {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                IconButton(
                                    onClick = {
                                        showSampleRequest = false
                                        showHelperText = false
                                        projectvm.setSampleRequestState(false)
                                        fetchSampleData() { sampleProjects, sampleNotes ->
                                            for (project in sampleProjects) {
                                                val sampleProject =
                                                    Project(
                                                        project.projectId,
                                                        project.projectName
                                                    )
                                                projectvm.addProject(sampleProject)
                                            }
                                            for (note in sampleNotes) {
                                                val sampleNote = Note(
                                                    note.noteId,
                                                    note.projectId,
                                                    note.front,
                                                    note.back
                                                )
                                                notevm.addNote(sampleNote)
                                            }
                                        }
                                    },
                                    modifier = Modifier.padding(end = 8.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = "Accept",
                                        tint = MaterialTheme.colors.secondaryVariant
                                    )
                                }
                                IconButton(
                                    onClick = {
                                        showSampleRequest = false
                                        projectvm.setSampleRequestState(false)
                                    },
                                    modifier = Modifier.padding(end = 8.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_close_24),
                                        contentDescription = "Delete",
                                        tint = MaterialTheme.colors.primaryVariant
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }



        AnimatedVisibility(
            visible = showHelperText,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Create a project ",
                        style = MaterialTheme.typography.subtitle1,
                        color = MaterialTheme.colors.secondaryVariant
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "",
                        tint = MaterialTheme.colors.secondaryVariant
                    )
                }
            }
        }
    }
}