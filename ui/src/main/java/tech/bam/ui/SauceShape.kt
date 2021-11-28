package tech.bam.ui

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class SauceShape(private val delta: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ) = Outline.Generic(path = drawWholePath(size = size, delta = delta))

    companion object {
        private fun drawWholePath(size: Size, delta: Float) = Path().apply {
            reset()
            lineTo(size.width - delta - 20f, 0f)
            cubicTo(
                size.width * 3 / 4 + delta,
                size.height * 3 / 4 - delta,
                size.width / 4 - delta,
                size.height / 4 - delta,
                0f,
                size.height - delta
            )
            lineTo(0f, 0f)
            close()
        }

        fun drawBorderPath(size: Size, delta: Float) = Path().apply {
            reset()
            moveTo(size.width - delta - 20f, 0f)
            cubicTo(
                size.width * 3 / 4 + delta,
                size.height * 3 / 4 - delta,
                size.width / 4 - delta,
                size.height / 4 - delta,
                0f,
                size.height - delta
            )
        }
    }
}