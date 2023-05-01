package edu.towson.cosc435.group12.mynotes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import kotlin.math.absoluteValue

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteBack(
    navController: NavController,
    notevm: NoteListViewModel,
    noteId: String
) {
    val note = notevm.getNote(noteId)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .size(300.dp)
                .aspectRatio(1.5f),
            shape = RoundedCornerShape(5.dp),
            elevation = 16.dp
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = note.back,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}