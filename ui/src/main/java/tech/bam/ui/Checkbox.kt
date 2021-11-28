package tech.bam.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@Preview
@Composable
fun Checkbox() {
    ClickHandler { clicks ->
        val isChecked = clicks
            .scan(initial = false) { isChecked, _ -> !isChecked }
            .collectAsState(initial = false)
        
        Stars(enabled = isChecked) {
            CheckboxFeedback(isChecked = isChecked)
        }
    }
}

@Composable
fun ClickHandler(content: @Composable (clicks: Flow<Unit>) -> Unit) {
    val composableScope = rememberCoroutineScope()
    val clicks = remember { MutableSharedFlow<Unit>() }

    Box(modifier = Modifier
        .clickable { composableScope.launch { clicks.emit(Unit) } }
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
            speed = if (enabled.value) .8f else -8f,
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
