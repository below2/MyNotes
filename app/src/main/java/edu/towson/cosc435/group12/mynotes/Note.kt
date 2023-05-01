package edu.towson.cosc435.group12.mynotes

data class Note(
    val noteId: String,
    val projectId: String,
    var front: String,
    var back: String,
    var isStudied: Boolean = false
    ) {
}