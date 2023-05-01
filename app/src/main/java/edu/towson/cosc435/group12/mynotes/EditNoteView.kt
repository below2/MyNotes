package edu.towson.cosc435.group12.mynotes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun EditNoteView(
    navController: NavHostController,
    notevm: NoteListViewModel,
    noteId: String
) {
    val focusManager = LocalFocusManager.current
    Box(modifier = Modifier
        .fillMaxSize()
        .clickable(onClick = { focusManager.clearFocus() }),
        contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var frontText by remember { mutableStateOf(notevm.getNote(noteId).front) }
            var backText by remember { mutableStateOf(notevm.getNote(noteId).back) }

            TextField(
                value = frontText,
                onValueChange = { frontText = it },
                label = { Text("Front of Note") },
                modifier = Modifier
                    .fillMaxWidth()
            )

            TextField(
                value = backText,
                onValueChange = { backText = it },
                label = { Text("Back of Note") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    notevm.editNote(noteId, frontText, backText)
                    navController.navigateUp()
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Done",
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
    }
}