package tech.bam.livecoding.ui.theme.instagram

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.bam.livecoding.ui.theme.BlueSea
import tech.bam.livecoding.ui.theme.GreenLeaves
import tech.bam.livecoding.ui.theme.GreenLemon
import tech.bam.livecoding.ui.theme.Typography

@Composable
fun InstagramScreen() {
    val isPressed = remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier =
        Modifier
            .background(
                Brush.linearGradient(
                    colors = listOf(GreenLemon, GreenLeaves, BlueSea),
                    start = Offset.Zero, end = Offset.Infinite
                )
            )
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isPressed.value = true
                        this.tryAwaitRelease()
                        isPressed.value = false
                    },
                    onLongPress = { /* This is empty so onTap is not fired on long press */ },
                    onTap = { Log.d("InstagramScreen", "onTap") }
                )
            }
    ) {
        InstagramSlicedProgressBar(2, 0, isPressed.value)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier =
            Modifier
                .weight(1f)
        ) {
            Text(
                text = "How you doin' ?",
                style = Typography.h1,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Tap or wait to go to the next screen",
                style = Typography.body1,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "👉",
                style = Typography.body1,
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun InstagramScreenPreview() {
    InstagramScreen()
}