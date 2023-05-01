package edu.towson.cosc435.group12.mynotes

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlin.math.absoluteValue
import androidx.compose.ui.util.lerp


@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun NoteListView(
    navController: NavController,
    projectId: String,
    notevm: NoteListViewModel
) {
    val projectNotes = notevm.getProjectNotes(projectId)
    val pagerState = rememberPagerState()

    HorizontalPager(pageCount = projectNotes.size, state = pagerState) { index ->
        NoteCard(navController, projectNotes[index], pagerState, index)
    }
}


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun NoteCard(navController: NavController, note: Note, pagerState: PagerState, index: Int) {
    Card(
        onClick = { navController.navigate(Routes.NoteBack.createRoute(note.noteId)) },
        Modifier
            .size(200.dp)
            .graphicsLayer {
                val pageOffset = (
                        (pagerState.currentPage - index) + pagerState
                            .currentPageOffsetFraction
                        ).absoluteValue

                alpha = lerp(
                    start = 0.5f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                )
            }
    ) {
        Column() {
            Text(note.noteId)
            Text(note.front)
            Text(note.back)
            Text(note.projectId)
            Text(note.isStudied.toString())
        }
    }
}