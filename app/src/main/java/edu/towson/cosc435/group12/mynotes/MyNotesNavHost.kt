package edu.towson.cosc435.group12.mynotes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun MyNotesNavHost(
    navController: NavHostController,
    startDestination: String = "projects"
) {
    val notevm: NoteListViewModel = viewModel()
    val notes by notevm.notes
    val projectvm: ProjectListViewModel = viewModel()
    val projects by projectvm.projects
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.Projects.route) {
            ProjectListView(navController, projects)
        }
        composable(Routes.NotesFront.route) {
            NoteListView(navController, notes)
        }
        composable(Routes.NoteBack.route) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")
            requireNotNull(noteId) { "noteId parameter was not found" }
            NoteBack(navController, noteId, notes)
        }
    }
}