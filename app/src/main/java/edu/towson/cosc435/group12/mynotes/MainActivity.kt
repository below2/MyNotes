package edu.towson.cosc435.group12.mynotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import edu.towson.cosc435.group12.mynotes.ui.theme.MyNotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyNotesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PopulateNotes()
                }
            }
        }
    }
}

@Composable
fun PopulateNotes() {
    val testNotes = (0..20).map { i ->
        Note("Project $i", "Front: $i", "Back: $i")
    }

    LazyColumn {
        items(testNotes.size) { index ->
            NoteRow(index, testNotes[index])
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyNotesTheme {
        PopulateNotes()
    }
}