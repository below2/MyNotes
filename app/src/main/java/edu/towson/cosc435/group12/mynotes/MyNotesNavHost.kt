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
            ProjectListView(navController, projectvm, notevm)
        }
        composable(Routes.NotesFront.route) { backStackEntry ->
            val projectId = backStackEntry.arguments?.getString("projectId")
            requireNotNull(projectId) { "projectId parameter was not found" }
            NoteCardListView(navController, projectId, notevm)
        }
        composable(Routes.NoteBack.route) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")
            requireNotNull(noteId) { "noteId parameter was not found" }
            NoteBack(navController, notevm, noteId)
        }
        composable(Routes.AddNote.route) {
            AddNoteView(navController, notevm, projectvm)
        }
        composable(Routes.AddProject.route) {
            AddProjectView(navController, projectvm)
        }
        composable(Routes.EditProject.route) { backStackEntry ->
            val projectId = backStackEntry.arguments?.getString("projectId")
            requireNotNull(projectId) { "projectId parameter was not found" }
            EditProjectView(navController, projectId, projectvm, notevm)
        }
        composable(Routes.EditNote.route) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")
            requireNotNull(noteId) { "noteId parameter was not found" }
            EditNoteView(navController, notevm, noteId)
        }
        composable(Routes.EditProjectName.route) { backStackEntry ->
            val projectId = backStackEntry.arguments?.getString("projectId")
            requireNotNull(projectId) { "projectId parameter was not found" }
            EditProjectNameView(navController, projectvm, projectId)
        }
    }
}
