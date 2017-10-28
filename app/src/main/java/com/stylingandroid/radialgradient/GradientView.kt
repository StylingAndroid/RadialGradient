package com.stylingandroid.radialgradient

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RadialGradient
import android.graphics.RectF
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View

class GradientView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0,
        defStyleRes: Int = 0,
        private val bounds: RectF = RectF()
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private val defaultColour: Int by lazyFast {
        context.theme.getColour(R.attr.colorAccent)
    }

    private val paint: Paint =
        Paint().apply {
            isAntiAlias = true
            style = Paint.Style.FILL
        }

    var shaderFactory: (width: Float, height: Float) -> Shader = { viewWidth, viewHeight ->
        RadialGradient(
                viewWidth / 2f,
                viewHeight / 2f,
                Math.min(viewWidth, viewHeight) / 2f,
                defaultColour,
                Color.TRANSPARENT,
                Shader.TileMode.CLAMP
        )
    }

    override fun onSizeChanged(newWidth: Int, newHeight: Int, oldWidth: Int, oldHeight: Int) =
            super.onSizeChanged(newWidth, newHeight, oldWidth, oldHeight).run {
                adjustBounds(newWidth.toFloat(), newHeight.toFloat())
            }

    private fun adjustBounds(width: Float, height: Float) {
        bounds.set(0f, 0f, width, height)
        paint.shader = shaderFactory(width, height)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawRect(bounds, paint)
    }
}
