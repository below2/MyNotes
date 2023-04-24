package edu.towson.cosc435.group12.mynotes

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun ProjectListView(
    navController: NavController,
    projects: List<Project>
) {
    LazyColumn {
        items(projects) { project ->
            ProjectRow(project) { navController.navigate(Routes.NotesFront.createRoute(project.projectId)) }
        }
    }
}