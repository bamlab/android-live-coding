package tech.bam.ui

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.scan

@ExperimentalCoroutinesApi
@Composable
fun Checkbox(content: @Composable () -> Unit) {
    ClickHandler { clicks ->
        val isChecked =
            remember {
                clicks.scan(initial = false) { isChecked, _ -> !isChecked }
            }
                .collectAsState(initial = false)


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Stars(enabled = isChecked) {
                CheckboxFeedback(isChecked = isChecked)
            }
            content()
        }
    }
}

@ExperimentalCoroutinesApi
@Composable
@Preview
fun PreviewCheckbox() {
    Checkbox {
        Text(text = "Check me")
    }
}

@Composable
fun ClickHandler(content: @Composable (clicks: Flow<Unit>) -> Unit) {
    val clicks = remember { MutableSharedFlow<Unit>() }
    val isPressed = remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .pointerInput(null) {
            detectTapGestures(
                onPress = {
                    isPressed.value = true
                    val released = this.tryAwaitRelease()
                    isPressed.value = false
                    if (released) clicks.emit(Unit)
                }
            )
        }
        .scale(if (isPressed.value) .9f else 1f)
    ) {
        content(clicks)
    }
}

@Composable
fun CheckboxFeedback(isChecked: State<Boolean>) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.check))
    val lottieAnimatable = rememberLottieAnimatable()

    LaunchedEffect(isChecked.value) {
        lottieAnimatable.animate(
            composition = composition,
            speed = if (isChecked.value) 2f else -2f
        )
    }

    Box(
        modifier = Modifier.padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier.size(32.dp),
            color = Color.White,
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp
        ) {
            LottieAnimation(composition, lottieAnimatable.progress)
        }
    }
}

@Composable
fun Stars(enabled: State<Boolean>, content: @Composable () -> Unit) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.stars))
    val lottieAnimatable = rememberLottieAnimatable()

    LaunchedEffect(enabled.value) {
        lottieAnimatable.animate(
            composition = composition,
            speed = if (enabled.value) .8f else 2f,
            iterations = if (!enabled.value) 0 else LottieConstants.IterateForever
        )
    }

    Box(contentAlignment = Alignment.Center) {
        Box(modifier = Modifier.padding(4.dp)) {
            content()
        }
        LottieAnimation(
            modifier = Modifier.matchParentSize(),
            composition = composition,
            progress = lottieAnimatable.progress
        )
    }
}
