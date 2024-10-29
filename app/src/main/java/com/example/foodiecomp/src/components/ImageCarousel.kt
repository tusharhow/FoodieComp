package com.example.foodiecomp.src.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay


@OptIn(ExperimentalPagerApi::class)
@Composable
fun AutoScrollingCarousel(
    images : List<String>
) {

    val pagerState = rememberPagerState()

    // Coroutine to auto-scroll pages
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000) // Change page every 3 seconds
            pagerState.animateScrollToPage((pagerState.currentPage + 1) % images.size)
        }
    }

    // Create the pager
    HorizontalPager(
        state = pagerState,
        count = images.size,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        AsyncImage(
            model = images[page],
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(200.dp).padding(16.dp)
        )
    }
}

