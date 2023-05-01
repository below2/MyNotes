package edu.towson.cosc435.group12.mynotes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SearchRow(
    navController: NavController,
    projectvm: ProjectListViewModel,
    note: Note
) {
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
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .weight(1.0f)
                        .padding(16.dp)
                ) {
                    Text(projectvm.getProject(note.projectId).projectName)
                    Divider(
                        modifier = Modifier
                            .fillMaxSize()
                            .width(1.dp)
                            .padding(top = 8.dp, bottom = 8.dp),
                        color = Color.Gray
                    )
                    Text(note.front)
                    Divider(
                        modifier = Modifier
                            .fillMaxSize()
                            .width(1.dp)
                            .padding(top = 8.dp, bottom = 8.dp),
                        color = Color.Gray
                    )
                    Text(note.back)
                }

                IconButton(
                    onClick = {
                        navController.navigate(Routes.EditNote.createRoute(note.noteId))
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
    }
}