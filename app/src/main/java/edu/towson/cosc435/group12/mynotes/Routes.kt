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
    object EditProject : Routes("{projectId}/editproject") {
        fun createRoute(projectId: String) = "$projectId/editproject"
    }
    object EditNote : Routes("{noteId}/editnote") {
        fun createRoute(noteId: String) = "$noteId/editnote"
    }
    object EditProjectName : Routes("{projectId}/editprojectname") {
        fun createRoute(projectId: String) = "$projectId/editprojectname"
    }
    object Search : Routes("search")

    object suggestedSearch : Routes ("suggested")
}
