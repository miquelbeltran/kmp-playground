package com.example.kmpplayground

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.composables.core.BottomSheet
import com.composables.core.DragIndication
import com.composables.core.ModalBottomSheet
import com.composables.core.Scrim
import com.composables.core.Sheet
import com.composables.core.SheetDetent
import com.composables.core.SheetDetent.Companion.FullyExpanded
import com.composables.core.SheetDetent.Companion.Hidden
import com.composables.core.rememberBottomSheetState
import com.composables.core.rememberModalBottomSheetState
import com.composeunstyled.Button
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import kmpplayground.composeapp.generated.resources.Res
import kmpplayground.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.linearGradient(listOf(Color(0xFF800080), Color(0xFFDA70D6))))
        ) {
            val sheetState = rememberModalBottomSheetState(
                initialDetent = Hidden,
                detents = listOf(Hidden, FullyExpanded)

            )
            val state = rememberWebViewState("https://example.com")


            Button(
                onClick = { sheetState.targetDetent = FullyExpanded },
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(WindowInsets.navigationBars.only(WindowInsetsSides.Horizontal).asPaddingValues()),
                shape = RoundedCornerShape(6.dp),
                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 10.dp),
                backgroundColor = Color.White
            ) {
                Text("Show Sheet", fontWeight = FontWeight(500))
            }

            val isCompact = maxWidth < 600.dp
            ModalBottomSheet(
                state = sheetState,
            ) {
                Scrim(scrimColor = Color.Black.copy(0.3f), enter = fadeIn(), exit = fadeOut())
                Box(
                    Modifier.fillMaxSize()
                        .padding(top = 12.dp)
                        .let { if (isCompact) it else it.padding(horizontal = 56.dp) }
                        .displayCutoutPadding()
                        .statusBarsPadding()
                        .padding(WindowInsets.navigationBars.only(WindowInsetsSides.Horizontal).asPaddingValues())) {
                    Sheet(
                        modifier = Modifier
                            .shadow(4.dp, RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
                            .widthIn(max = 640.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
                        contentPadding = WindowInsets.ime.asPaddingValues(),
                        backgroundColor = Color.White,
                        contentColor = Color.Black
                    ) {
                        Box(Modifier.fillMaxWidth().height(600.dp)) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                DragIndication(
                                    modifier = Modifier.padding(top = 22.dp)
                                        .background(Color.Black.copy(0.4f), RoundedCornerShape(100))
                                        .width(32.dp)
                                        .height(4.dp)
                                )
                                WebView(state)
                            }
                        }
                    }
                }
            }
        }
    }
}