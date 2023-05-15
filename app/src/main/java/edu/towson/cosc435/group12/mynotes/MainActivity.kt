package edu.towson.cosc435.group12.mynotes

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.*
import java.util.*

//import edu.towson.cosc435.group12.mynotes.ui.theme.MyNotesTheme

class MainActivity : ComponentActivity() {
    //TODO: extract all of this to its own class and just have MainActivity call that class
    @OptIn(ExperimentalPermissionsApi::class)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val projectvm: ProjectListViewModel = viewModel()
            val notevm: NoteListViewModel = viewModel()
            popDbData(projectvm, notevm)
            MyNotesTheme {
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = {
                            TopBar(navController, application.applicationContext)
                        },
                        floatingActionButton = {
                            AddButton(navController)
                        }
                    ) {
                        MyNotesNavHost(projectvm, notevm, navController)
                    }
                }
            }
        }
    }
}

@Composable
private fun popDbData(projectvm: ProjectListViewModel, notevm: NoteListViewModel) {
    val projectDatabase = ProjectDatabase.getInstance(LocalContext.current)
    val noteDatabase = NoteDatabase.getInstance(LocalContext.current)
    val projectDao = projectDatabase.projectDao()
    val noteDao = noteDatabase.noteDao()

    LaunchedEffect(Unit) {
//        val deleteDeferred = CompletableDeferred<Unit>()
//
//        withContext(Dispatchers.IO) {
//            projectDao.deleteAllProjects()
//            noteDao.deleteAllNotes()
//            deleteDeferred.complete(Unit)
//        }
//
//        deleteDeferred.await()

        val projects = withContext(Dispatchers.IO) {
            projectDao.getAllProjects()
        }
        val notes = withContext(Dispatchers.IO) {
            noteDao.getAllNotes()
        }


        if (projects.isNotEmpty()) {
            for (project in projects) {
                projectvm.addProject(project)
            }
        }

        if (notes.isNotEmpty()) {
            for (note in notes) {
                notevm.addNote(note)
            }
        }
    }
}

@Composable
private fun TopBar(navController: NavHostController, ctx: Context) {
    val currentRoute = navController
        .currentBackStackEntryFlow
        .collectAsState(initial = navController.currentBackStackEntry)
    val showBackButton = when (currentRoute.value?.destination?.route) {
        Routes.Projects.route -> false
        null -> false
        else -> true
    }
    val navBackTwice = when (currentRoute.value?.destination?.route) {
        Routes.NoteBack.route -> true
        else -> false
    }
    val showSearchButton = when (currentRoute.value?.destination?.route) {
        Routes.EditProject.route -> false
        Routes.EditNote.route -> false
        Routes.AddProject.route -> false
        Routes.AddNote.route -> false
        else -> true
    }

    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (showBackButton) {
                    if (navBackTwice) {
                        IconButton(onClick = {
                            navController.popBackStack()
                            navController.popBackStack()
                        }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    } else {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    }
                }
                Text("MyNotes")
                Spacer(modifier = Modifier.weight(1f))

                if (showSearchButton) {
                    IconButton(onClick = {
                        navController.navigate(Routes.Search.route)
                    }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                } else {
                    IconButton(onClick = {
                        val url = "https://puginarug.com/"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        }
                        ctx.startActivity(intent)
                    }) {
                        Icon(Icons.Default.Info, contentDescription = "Info")
                    }
                }

            }
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White,
        elevation = 10.dp
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AddButton(navController: NavHostController) {
    val currentRoute = navController
        .currentBackStackEntryFlow
        .collectAsState(initial = navController.currentBackStackEntry)
    val showAddButton = when (currentRoute.value?.destination?.route) {
        Routes.AddNote.route -> false
        Routes.AddProject.route -> false
        Routes.EditNote.route -> false
        Routes.EditProjectName.route -> false
        Routes.Search.route -> false
        else -> true
    }
    var showOptions by remember { mutableStateOf(false) }
    if (showAddButton) {
        FloatingActionButton(
            onClick = {
                when (navController.currentDestination?.route) {
                    Routes.EditProject.route -> navController.navigate(Routes.AddNote.route)
                    Routes.NotesFront.route -> navController.navigate(Routes.AddNote.route)
                    Routes.Projects.route -> showOptions = !showOptions
                }
            },
            backgroundColor = MaterialTheme.colors.primary
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add")
        }
        AnimatedVisibility(
            visible = showOptions,
            enter = scaleIn(),
            exit = scaleOut()
        ) {
            Box {
                FloatingActionButton(
                    onClick = {
                        showOptions = false
                        navController.navigate(Routes.AddNote.route)
                    },
                    backgroundColor = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .offset(y = (-72).dp)
                ) {
                    Text(
                        text = "N",
                        style = MaterialTheme.typography.subtitle1
                    )
                }
                FloatingActionButton(
                    onClick = {
                        showOptions = false
                        navController.navigate(Routes.AddProject.route)
                    },
                    backgroundColor = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .offset(x = (-72).dp)
                ) {
                    Text(
                        text = "P",
                        style = MaterialTheme.typography.subtitle1
                    )
                }
            }
        }
    }
}

