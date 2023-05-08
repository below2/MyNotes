package edu.towson.cosc435.group12.mynotes

import android.Manifest
import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

//import edu.towson.cosc435.group12.mynotes.ui.theme.MyNotesTheme

class MainActivity : ComponentActivity() {
    //TODO: extract all of this to its own class and just have MainActivity call that class
    @OptIn(ExperimentalPermissionsApi::class)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyNotesTheme {
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = {
                            TopBar(navController)
                        },
                        floatingActionButton = {
                            AddButton(navController)
                        }
                    ) {
                        MyNotesNavHost(navController)
                    }
                }
            }

            if (Intent.ACTION_SEARCH == intent.action){
                intent.getStringExtra(SearchManager.QUERY)?.also {
                    query -> SearchRecentSuggestions(this, suggestedSearch.AUTHORITY, suggestedSearch.MODE)
                    .saveRecentQuery(query, null)
                }
            }
        }
    }
}

@Composable
private fun TopBar(navController: NavHostController) {
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
                IconButton(onClick = {
                    navController.navigate(Routes.Search.route)
                }) {
                    Icon(Icons.Default.Search, contentDescription = "Search")
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

