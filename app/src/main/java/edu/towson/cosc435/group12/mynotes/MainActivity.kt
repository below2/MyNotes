package edu.towson.cosc435.group12.mynotes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import edu.towson.cosc435.group12.mynotes.ui.theme.MyNotesTheme

class MainActivity : ComponentActivity() {
    //TODO: extract all of this to its own class and just have MainActivity call that class
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
        }
    }
}

@Composable
private fun TopBar(navController: NavHostController) {
    TopAppBar(
        title = { Text("MyNotes") },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White,
        elevation = 10.dp,
        navigationIcon = {
            //TODO: have the IconButton only display when not on startDestination
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
    )
}

@Composable
private fun AddButton(navController: NavHostController) {
    FloatingActionButton(
        onClick = { navController.navigate(Routes.AddNote.route) },
        backgroundColor = Color.Green
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Add")
    }
}