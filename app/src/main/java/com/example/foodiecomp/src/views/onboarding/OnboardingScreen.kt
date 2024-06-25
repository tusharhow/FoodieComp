package com.example.foodiecomp.src.views.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.material.TextButton
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodiecomp.src.components.SvgImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.pager.HorizontalPagerIndicator
import kotlinx.coroutines.launch

data class OnboardingPage( val title: String, val description: String, val image: String? = null)


@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(pages: List<OnboardingPage>, onFinish: () -> Unit ) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            count = pages.size,
            state =  pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            OnboardingPageUI(page = pages[page])
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
            spacing = 8.dp,
            activeColor = MaterialTheme.colorScheme.primary,
            inactiveColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
        )

         Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().padding(16.dp)
         ){
             if (pagerState.currentPage > 0) {
                    TextButton(onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }) {
                        Text("Previous")
                    }
             }
                if (pagerState.currentPage < pages.size - 1) {
                    TextButton(onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }) {
                        Text("Skip")
                    }
                }
                if (pagerState.currentPage == pages.size - 1) {
                    Button(onClick = onFinish) {
                        Text("Get Started")
                    }
                }
         }
    }
}

@Composable
fun OnboardingPageUI(page: OnboardingPage) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SvgImage(
            assetName =  page.image!!,
            modifier = Modifier.size(300.dp),
        )

        Spacer(modifier = Modifier.height(16.dp*2))
        Text(text = page.title, style = TextStyle(
            fontSize = 22.sp,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight =  FontWeight.Bold
        ))
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = page.description, style = TextStyle(
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.onSurface
        ), textAlign = TextAlign.Center)
    }
}