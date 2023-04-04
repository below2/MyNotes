package edu.towson.cosc435.group12.mynotes

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun ProjectListView(
    navController: NavController,
    projects: List<Project>
) {
    LazyColumn {
        items(projects.size) { index ->
            ProjectRow(projects[index]) { navController.navigate(Routes.NotesFront.route) }
        }
    }
}