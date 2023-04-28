package edu.towson.cosc435.group12.mynotes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MyNotesNavHost(
    navController: NavHostController,
    startDestination: String = Routes.Projects.route
) {
    val projectvm: ProjectListViewModel = viewModel()
    val projects by projectvm.projects
    val notevm: NoteListViewModel = viewModel()
    val notes by notevm.notes
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.Projects.route) {
            ProjectListView(navController, projects)
        }
        composable(Routes.NotesFront.route) { backStackEntry ->
            val projectId = backStackEntry.arguments?.getString("projectId")
            requireNotNull(projectId) { "projectId parameter was not found" }
            NoteListView(navController, projectId, notevm)
        }
        composable(Routes.NoteBack.route) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")
            requireNotNull(noteId) { "noteId parameter was not found" }
            NoteBack(navController, noteId, notes)
        }
        composable(Routes.AddNote.route) {
            AddNoteView(navController, notevm, projects)
        }
        composable(Routes.AddProject.route) {
            AddProjectView(navController, projectvm)
        }
    }
}
