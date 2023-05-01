package edu.towson.cosc435.group12.mynotes

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
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
    val projectListState = projectvm.projects
    LazyColumn {
        items(
            items = projectListState.value,
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
}