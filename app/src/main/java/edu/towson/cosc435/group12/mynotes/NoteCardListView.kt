package edu.towson.cosc435.group12.mynotes

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlin.math.absoluteValue
import androidx.compose.ui.util.lerp


@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun NoteCardListView(
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
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .size(300.dp)
                .aspectRatio(1.5f)
                .clickable(onClick = { navController.navigate(Routes.NoteBack.createRoute(note.noteId)) })
                .combinedClickable(
                    onClick = { navController.navigate(Routes.NoteBack.createRoute(note.noteId)) },
                    onLongClick = { navController.navigate(Routes.EditNote.createRoute(note.noteId)) }
                )
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
                },
            shape = RoundedCornerShape(5.dp),
            elevation = 16.dp,
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = note.front,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}