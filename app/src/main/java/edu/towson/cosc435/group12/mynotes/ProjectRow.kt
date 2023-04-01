package edu.towson.cosc435.group12.mynotes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProjectRow(
    idx: Int,
    project: Project,
    onClick: () -> Unit = {}
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(5.dp),
        elevation = 16.dp,
        modifier = Modifier
            .padding(start=16.dp, end=16.dp, top=5.dp, bottom=5.dp)
            .fillMaxWidth()
    ) {
            Text(project.projectName)
    }
}

@Composable
fun PopulateProjects(navController: NavController) {
    val testProjects = (0..10).map { i ->
        Project("Project $i")
    }

    LazyColumn {
        items(testProjects.size) { index ->
            ProjectRow(index, testProjects[index]) { navController.navigate("notes") }
        }
    }
}