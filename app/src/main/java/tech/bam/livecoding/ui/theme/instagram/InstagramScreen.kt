package tech.bam.livecoding.ui.theme.instagram

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import tech.bam.livecoding.ui.theme.BlueSea
import tech.bam.livecoding.ui.theme.GreenLeaves
import tech.bam.livecoding.ui.theme.GreenLemon

@Composable
fun InstagramScreen() {
    Column(
        Modifier
            .background(
                Brush.linearGradient(
                    colors = listOf(GreenLemon, GreenLeaves, BlueSea),
                    start = Offset.Zero, end = Offset.Infinite
                )
            )
            .fillMaxSize()
    ) {

    }
}

@Preview
@Composable
fun InstagramScreenPreview() {
    InstagramScreen()
}