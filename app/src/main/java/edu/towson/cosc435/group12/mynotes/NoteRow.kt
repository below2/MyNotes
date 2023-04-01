package edu.towson.cosc435.group12.mynotes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun NoteRow(
    idx: Int,
    note: Note
) {
    Card(
        shape = RoundedCornerShape(5.dp),
        elevation = 16.dp,
        modifier = Modifier
            .padding(start=16.dp, end=16.dp, top=5.dp, bottom=5.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(start=16.dp, end=16.dp, top=5.dp, bottom=5.dp),
            Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(note.front)
            Text(note.back)
        }
    }
}

@Composable
fun PopulateNotes(navController: NavController) {
    val testNotes = (0..20).map { i ->
        Note("Front: $i", "Back: $i")
    }

    LazyColumn {
        items(testNotes.size) { index ->
            NoteRow(index, testNotes[index])
        }
    }
}