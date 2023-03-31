package edu.towson.cosc435.group12.mynotes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NoteRow(
    idx: Int, //???
    note: Note
) {
    Card(
        shape = RoundedCornerShape(5.dp),
        elevation = 20.dp,
        modifier = Modifier
            .padding(start=16.dp, end=16.dp, top=5.dp, bottom=5.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(start=16.dp, end=16.dp, top=5.dp, bottom=5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(note.project)
            Row(
                modifier = Modifier
                    .padding(start=16.dp, end=16.dp, top=5.dp, bottom=5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(note.front)
                Text(note.back)
            }
        }
    }
}