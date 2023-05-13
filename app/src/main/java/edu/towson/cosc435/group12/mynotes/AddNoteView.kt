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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun AddNoteView(
    navController: NavHostController,
    notevm: NoteListViewModel,
    projectvm: ProjectListViewModel
) {
    val projects = projectvm.getProjects()
    var expanded by remember { mutableStateOf(false) }

    if (projects.isNotEmpty()) {
        var selectedProject by remember { mutableStateOf(projects.first()) }
        val focusManager = LocalFocusManager.current
        Box(modifier = Modifier
            .fillMaxSize()
            .clickable(onClick = { focusManager.clearFocus() }),
            contentAlignment = Alignment.Center) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    TextField(
                        readOnly = true,
                        value = selectedProject.projectName,
                        onValueChange = { },
                        label = { Text("Projects") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = expanded
                            )
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(),
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }) {
                        projects.forEach { project ->
                            DropdownMenuItem(onClick = {
                                selectedProject = project
                                expanded = false
                            }) {
                                Text(project.projectName)
                            }
                        }
                    }
                }

                var frontText by remember { mutableStateOf("") }
                var backText by remember { mutableStateOf("") }
                val keyboardController = LocalSoftwareKeyboardController.current

                TextField(
                    value = frontText,
                    onValueChange = { frontText = it },
                    label = { Text("Front of Note") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() })
                )

                TextField(
                    value = backText,
                    onValueChange = { backText = it },
                    label = { Text("Back of Note") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() })
                )

                Button(
                    onClick = {
                        notevm.addNote(selectedProject.projectId, frontText, backText)
                        navController.navigateUp()
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                    contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Create",
                        style = MaterialTheme.typography.subtitle1
                    )
                }
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = "You must create a project before creating a note",
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center
            )
        }
    }
}