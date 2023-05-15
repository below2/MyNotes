package edu.towson.cosc435.group12.mynotes

data class SampleProject(
    val id: Int,
    val projectId: String,
    val projectName: String
)

data class SampleNote(
    val id: Int,
    val noteId: String,
    val projectId: String,
    val front: String,
    val back: String
)

data class SampleData(
    val projects: List<SampleProject>,
    val notes: List<SampleNote>
)