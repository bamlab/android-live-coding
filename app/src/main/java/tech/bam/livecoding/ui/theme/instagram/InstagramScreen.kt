package tech.bam.livecoding.ui.theme.instagram

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import tech.bam.livecoding.ui.theme.BlueSea
import tech.bam.livecoding.ui.theme.GreenLeaves
import tech.bam.livecoding.ui.theme.GreenLemon
import tech.bam.livecoding.ui.theme.Typography

@Composable
fun InstagramScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier =
        Modifier
            .background(
                Brush.linearGradient(
                    colors = listOf(GreenLemon, GreenLeaves, BlueSea),
                    start = Offset.Zero, end = Offset.Infinite
                )
            )
            .fillMaxSize()
    ) {
        Text(text = "How you doin' ?", style = Typography.h1, color = Color.White)
        Text(
            text = "Tap or wait to go to the next screen",
            style = Typography.body1,
            color = Color.White
        )
        Text(text = "👉", style = Typography.body1, color = Color.White)
    }
}

@Preview
@Composable
fun InstagramScreenPreview() {
    InstagramScreen()
}