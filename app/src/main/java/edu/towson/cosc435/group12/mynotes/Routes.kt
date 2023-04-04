package edu.towson.cosc435.group12.mynotes

sealed class Routes(var route: String) {
    object NotesFront : Routes("notesfront")
    object NoteBack : Routes("{noteId}/noteback") {
        fun createRoute(noteId: String) = "$noteId/noteback"
    }
    object Projects : Routes("projects")
}