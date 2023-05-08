package edu.towson.cosc435.group12.mynotes

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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