package tech.bam.livecoding.rating

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StarRating(modifier: Modifier = Modifier, max: Int = 5) {
    BoxWithConstraints(
        modifier = modifier, contentAlignment = Alignment.Center
    ) {
        val width = maxWidth
        val widthPx = with(LocalDensity.current) { width.toPx() }

        val swipeableState = rememberSwipeableState(0)
        val coroutineScope = rememberCoroutineScope()

        val anchors: Map<Float, Int> = (0..max).fold(mutableMapOf()) { acc, i ->
            acc[widthPx / max * i] = i
            acc
        }

        Row(
            modifier = Modifier
                .width(width)
                .fillMaxHeight()
                .swipeable(
                    state = swipeableState,
                    anchors = anchors,
                    thresholds = { _, _ -> FractionalThreshold(0.3f) },
                    orientation = Orientation.Horizontal
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            (0 until max).forEach { i ->
                val sizePercent = (swipeableState.offset.value / widthPx * max - i).coerceIn(0f, 1f)
                val isActive = swipeableState.targetValue > i
                Star(
                    size = width / max,
                    sizePercent = sizePercent,
                    isActive = isActive,
                    modifier = Modifier.clickable {
                        coroutineScope.launch {
                            swipeableState.animateTo(
                                targetValue = i + 1, anim = tween(
                                    durationMillis = 300,
                                    easing = FastOutLinearInEasing
                                )
                            )
                        }
                    })
            }
        }
    }
}

@Preview
@Composable
fun StarRatingPreview() {
    StarRating(modifier = Modifier.width(240.dp))
}