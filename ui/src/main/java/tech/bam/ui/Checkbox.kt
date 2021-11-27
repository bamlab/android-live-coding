package tech.bam.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

        CheckboxFeedback(isChecked = isChecked)
    }
}

@Composable
fun ClickHandler(content: @Composable (clicks: Flow<Unit>) -> Unit) {
    val composableScope = rememberCoroutineScope()
    val clicks = remember { MutableSharedFlow<Unit>() }

    Box(modifier = Modifier
        .clickable { composableScope.launch { clicks.emit(Unit) } }) {
        content(clicks)
    }
}

@Composable
fun CheckboxFeedback(isChecked: State<Boolean>) {
    Box(
        modifier = Modifier
            .size(600.dp)
            .background(if (isChecked.value) Color.Green else Color.Red)
    )
}
