package tech.bam.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Preview(showSystemUi = true, showBackground = false)
@Composable
fun RoyalCheeseScreen() {
    MaterialTheme(typography = typography) {
        Box(modifier = Modifier
            .background(Color.White)
            .fillMaxSize()) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "ROYAL CHEESE",
                    style = MaterialTheme.typography.h1,
                    textAlign = TextAlign.Center
                )
                Checkbox {
                    Text(
                        text = "Salad ?",
                        style = MaterialTheme.typography.body1,
                    )
                }
            }
        }
    }
}