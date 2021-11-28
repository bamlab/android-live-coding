package tech.bam.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Preview(showSystemUi = true, showBackground = false)
@Composable
fun RoyalCheeseScreen() {
    val animationValues = (0..3).map { Animatable(0f) }

    LaunchedEffect(null) {
        animationValues.mapIndexed { index, animatable ->
            animatable.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 100,
                )
            )
        }
    }

    MaterialTheme(typography = typography) {
        Box(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
        ) {
            Sauce(
                modifier = Modifier
                    .alpha(animationValues[0].value)
                    .align(Alignment.TopStart),
                color = Color(
                    249, 83, 79
                )
            )

            Column(
                modifier = Modifier.align(Alignment.Center),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "ROYAL CHEESE",
                    style = MaterialTheme.typography.h1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.alpha(animationValues[1].value)
                )
                ClickHandler {
                    Surface(
                        shape = RoundedCornerShape(24.dp, 24.dp, 4.dp, 4.dp),
                        elevation = 4.dp,
                        color = Color(253, 183, 84),
                        modifier = Modifier
                            .height(48.dp)
                            .width(160.dp)
                    ) { }
                }
                ClickHandler {
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        elevation = 4.dp,
                        color = Color(211, 143, 82),
                        modifier = Modifier
                            .height(48.dp)
                            .width(160.dp)
                    ) { }
                }
                Checkbox(modifier = Modifier.alpha(animationValues[2].value)) {
                    Text(
                        text = "Salad ?",
                        style = MaterialTheme.typography.body1,
                    )
                }
                ClickHandler {
                    Surface(
                        shape = RoundedCornerShape(4.dp, 4.dp, 24.dp, 24.dp),
                        elevation = 4.dp,
                        color = Color.White,
                        modifier = Modifier
                            .height(48.dp)
                            .width(160.dp)
                    ) { }
                }
            }


            Sauce(
                modifier = Modifier
                    .alpha(animationValues[3].value)
                    .align(Alignment.BottomEnd)
                    .rotate(180f),
                color = Color(253, 187, 37)
            )
        }
    }
}