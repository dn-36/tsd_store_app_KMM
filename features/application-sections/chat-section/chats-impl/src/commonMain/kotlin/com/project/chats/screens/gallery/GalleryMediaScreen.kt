package com.project.chats.screens.gallery

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.skydoves.landscapist.coil3.CoilImage




class GalleryMediaScreen(
    private val imageUrls: List<String>,
    private val initPossition:Int = 0
) : Screen {

    @Composable
    override fun Content() {
        ImageViewer(imageUrls, initPossition)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageViewer(imageUrls: List<String>,initPossition:Int = 0) {
    val pagerState = rememberPagerState(initPossition){imageUrls.size}

    Box(modifier = Modifier.fillMaxSize()) {
        // ViewPager for images
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            FullScreenImage(imageUrl = imageUrls[page])
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.5f))
                .padding(8.dp)
                .align(Alignment.TopCenter)
        ) {
            Text(
                text = "${pagerState.currentPage + 1} / ${imageUrls.size}",
                color = Color.White,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun FullScreenImage(imageUrl: String) {
    CoilImage(
        imageModel = { imageUrl },
        modifier = Modifier.wrapContentSize()
    )
}