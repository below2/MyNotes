package edu.towson.cosc435.group12.mynotes

import androidx.compose.ui.tooling.preview.Preview

data class Note(
    val id: Long,
    val noteId: String,
    val projectId: String,
    var front: String,
    var back: String
) {
    constructor(noteId: String, projectId: String, front: String, back: String) : this(-1, noteId, projectId, front, back)
}
