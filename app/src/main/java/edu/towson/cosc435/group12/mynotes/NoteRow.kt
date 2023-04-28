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
    notevm: NoteListViewModel,
    onClick: () -> Unit = {}
) {
//    val noteListState = remember { notevm.notes.value.toMutableStateList() }
    var showCheckbox by remember { mutableStateOf(notevm.getNotes()) }
    var isSelected by remember { mutableStateOf(notevm.getNote(note.noteId).isSelected) }

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
                .combinedClickable(
                    onClick = onClick,
                    onLongClick = {
                        notevm.readyDelete(note)
//                        noteListState.find { it.noteId == note.noteId }?.isSelected = true
//                        showCheckbox = !showCheckbox
                    }
                )
        ) {
            Text(note.front)
        }
        if (showCheckbox.any { it.isSelected }) {
            Checkbox(
                modifier = Modifier.align(Alignment.TopEnd),
                checked = isSelected,
                onCheckedChange = {
                    notevm.selectNote(note)
                    isSelected = !isSelected
                }
            )
        }
    }

}

