package edu.towson.cosc435.group12.mynotes

import androidx.compose.ui.tooling.preview.Preview

data class Note(
    val noteId: String,
    val projectId: String,
    var front: String,
    var back: String
    ) {
}