package edu.towson.cosc435.group12.mynotes

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchListView(
    navController: NavController,
    projectvm: ProjectListViewModel,
    notevm: NoteListViewModel
) {
    val notes = notevm.getNotes()
    var searchText by remember { mutableStateOf("") }
    var foundNotes = listOf<Note>()

    for (note in notes) {
        if (
            note.front.contains(searchText, ignoreCase = true) ||
            note.back.contains(searchText, ignoreCase = true) ||
            projectvm.getProject(note.projectId).projectName.contains(searchText, ignoreCase = true)
        ) {
            foundNotes += note
        }
    }
    Column() {
        val keyboardController = LocalSoftwareKeyboardController.current
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Search Notes") },
            modifier = Modifier
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() })
        )

        LazyColumn {
            items(
                items = foundNotes,
                key = { note -> note.noteId },
                itemContent = { note ->
                    SearchRow(
                        navController = navController,
                        projectvm = projectvm,
                        note = note
                    )
                }
            )
        }
    }
}