package edu.towson.cosc435.group12.mynotes

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun NoteRow(
    note: Note,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Card(
            onClick = onClick,
            shape = RoundedCornerShape(5.dp),
            elevation = 16.dp,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 5.dp, bottom = 5.dp)
                .fillMaxWidth()
        ) {
            Text(note.front)
        }
    }

}

