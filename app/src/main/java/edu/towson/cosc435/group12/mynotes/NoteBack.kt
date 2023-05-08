package edu.towson.cosc435.group12.mynotes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
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
            IconButton(
                onClick = {
                    navController.popBackStack()
                },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_flip_to_front_24),
                    contentDescription =  "Flip to front",
                    tint = Color.Gray
                )
            }
        }
    }
}