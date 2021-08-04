package tech.bam.livecoding.rating

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StarRating(modifier: Modifier = Modifier, max: Int = 5) {
    BoxWithConstraints(
        modifier = modifier, contentAlignment = Alignment.Center
    ) {
        val width = maxWidth
        val widthPx = with(LocalDensity.current) { width.toPx() }

        val offset = remember { mutableStateOf(Offset.Zero) }

        val value = (offset.value.x * 2 * max / widthPx).roundToInt().toFloat() / 2

        val animatedOffset = remember { Animatable(0f) }

        LaunchedEffect(value) {
            animatedOffset.animateTo(value)
        }

        Row(
            modifier = Modifier
                .width(width)
                .fillMaxHeight()
                .pointerInput(Unit) {
                    detectDragGestures(onDragStart = { downOffset ->
                        offset.value = downOffset
                    }, onDrag = { _, dragAmount ->
                        offset.value += dragAmount
                    }, onDragEnd = {
                        offset.value = Offset(
                            (offset.value.x * 2)
                                .roundToInt()
                                .toFloat() / 2, 0f
                        )
                    })
                }
                .pointerInput(Unit) {
                    detectTapGestures { downOffset ->
                        offset.value = downOffset
                    }
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            (0 until max).forEach { i ->
                val sizePercent = (offset.value.x / widthPx * max - i).coerceIn(0f, 1f)
                val isActive = animatedOffset.value > i
                Star(
                    size = width / max,
                    sizePercent = sizePercent,
                    isActive = isActive,
                    delayMillis = 100 * i
                )
            }
        }
    }
}

@Preview(heightDp = 100)
@Composable
fun StarRatingPreview() {
    StarRating(modifier = Modifier.width(240.dp))
}