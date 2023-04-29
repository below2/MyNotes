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
import androidx.compose.ui.unit.dp

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
                val currentProject by rememberUpdatedState(project)
                val dismissState = rememberDismissState(
                    confirmStateChange = {
                        if (it == DismissValue.DismissedToStart) {
                            projectvm.removeProject(currentProject, notevm)
                            true
                        } else false
                    }
                )

                if (dismissState.isDismissed(DismissDirection.EndToStart)
                ) {
                    projectvm.removeProject(project, notevm)
                }

                SwipeToDismiss(
                    state = dismissState,
                    modifier = Modifier
                        .padding(vertical = 1.dp)
                        .animateItemPlacement(),
                    directions = setOf(DismissDirection.EndToStart),
                    background = {
                        SwipeBackground(dismissState)
                    },
                    dismissContent = {
                        ProjectRow(project) {
                            navController.navigate(
                                Routes.NotesFront.createRoute(
                                    project.projectId
                                )
                            )
                        }
                    }
                )
            }
        )
    }
}

//@Composable
//fun ProjectListView(
//    navController: NavController,
//    projects: List<Project>
//) {
//    LazyColumn {
//        items(projects) { project ->
//            ProjectRow(project) { navController.navigate(Routes.NotesFront.createRoute(project.projectId)) }
//        }
//    }
//}