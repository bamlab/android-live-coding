package tech.bam.livecoding.instagram

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tech.bam.livecoding.ui.theme.BlueSea
import tech.bam.livecoding.ui.theme.GreenLeaves
import tech.bam.livecoding.ui.theme.GreenLemon
import tech.bam.livecoding.ui.theme.Purple
import tech.bam.livecoding.ui.theme.RedRaspberry
import tech.bam.livecoding.ui.theme.Typography

@Composable
fun InstagramScreen(navController: NavController, steps: Int, currentStep: Int) {
    val isPressed = remember { mutableStateOf(false) }

    val goToNextScreen = {
        if (currentStep + 1 <= steps) navController.navigate("instagram/$steps/${currentStep + 1}")
    }
    val goToPreviousScreen = {
        if (currentStep - 1 > 0) navController.navigate("instagram/$steps/${currentStep - 1}")
    }

    val backgroundColors = when (currentStep % 3) {
        0 -> listOf(GreenLemon, GreenLeaves, BlueSea)
        1 -> listOf(GreenLeaves, BlueSea, Purple)
        else -> listOf(BlueSea, Purple, RedRaspberry)
    }

    val textTypo = when (currentStep % 2) {
        0 -> Typography.h1
        else -> Typography.h2
    }

    val textContent = when (currentStep % 3) {
        0 -> "Wow incredible !"
        1 -> "Excellent !"
        else -> "How you doin' ?"
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier =
        Modifier
            .background(
                Brush.linearGradient(
                    colors = backgroundColors,
                    start = Offset.Zero, end = Offset.Infinite
                )
            )
            .pointerInput(Unit) {
                val maxWidth = this.size.width
                detectTapGestures(
                    onPress = {
                        val pressStartTime = System.currentTimeMillis()
                        isPressed.value = true
                        this.tryAwaitRelease()
                        val pressEndTime = System.currentTimeMillis()
                        val totalPressTime = pressEndTime - pressStartTime
                        if (totalPressTime < 200) {
                            val isTapOnRightThreeQuarters = (it.x > (maxWidth / 4))
                            if (isTapOnRightThreeQuarters) {
                                goToNextScreen()
                            } else {
                                goToPreviousScreen()
                            }
                        }
                        isPressed.value = false
                    },
                )
            }
    ) {
        InstagramSlicedProgressBar(steps, currentStep, isPressed.value, goToNextScreen)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier =
            Modifier
                .weight(1f)
        ) {
            Text(
                text = textContent,
                style = textTypo,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Tap or wait to go to the next screen",
                style = Typography.body1,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "👉",
                style = Typography.body1,
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun InstagramScreenPreview() {
    val navController = rememberNavController()
    InstagramScreen(navController, 3, 1)
}