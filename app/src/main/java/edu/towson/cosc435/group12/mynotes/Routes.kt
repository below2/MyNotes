package edu.towson.cosc435.group12.mynotes

sealed class Routes(var route: String) {
    object NotesFront : Routes("{projectId}/notesfront") {
        fun createRoute(projectId: String) = "$projectId/notesfront"
    }
    object NoteBack : Routes("{noteId}/noteback") {
        fun createRoute(noteId: String) = "$noteId/noteback"
    }
    object Projects : Routes("projects")
    object AddNote : Routes("addnote")
    object AddProject : Routes("addproject")
}