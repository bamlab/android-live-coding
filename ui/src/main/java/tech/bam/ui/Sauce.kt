package tech.bam.ui

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Sauce(modifier: Modifier = Modifier, color: Color) {
    val deltaAnim = rememberInfiniteTransition()
    val d by deltaAnim.animateFloat(
        initialValue = 0f,
        targetValue = 100f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )


    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .graphicsLayer {
                shadowElevation = 20.dp.toPx()
                shape = SauceShape(d)
                clip = true
            }
            .background(color)
            .drawBehind {
                drawPath(
                    path = SauceShape.drawBorderPath(size = size, delta = d),
                    color = Color.White,
                    style = Stroke(width = 20.dp.toPx())
                )
            },
    )
}

@Preview
@Composable
fun PreviewSauce() {
    Box(modifier = Modifier.size(400.dp, 100.dp)) {
        Sauce(color = Color(249, 83, 79))
    }
}