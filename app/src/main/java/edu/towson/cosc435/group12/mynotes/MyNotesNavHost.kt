package edu.towson.cosc435.group12.mynotes

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MyNotesNavHost(
    navController: NavHostController,
    startDestination: String = "projects"
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.Projects.route) {
            PopulateProjects(navController)
        }
        composable(Routes.Notes.route) {
            PopulateNotes(navController)
        }
    }
}