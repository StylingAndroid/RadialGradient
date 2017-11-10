package com.stylingandroid.radialgradient

import android.graphics.Color
import android.graphics.RadialGradient
import android.graphics.Shader
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val defaultColour: Int by lazyFast {
        theme.getColour(R.attr.colorAccent)
    }

    private val colours: IntArray by lazyFast {
        arrayOf(defaultColour,
                defaultColour.setAlpha(0xA7),
                defaultColour.setAlpha(0x60),
                defaultColour.setAlpha(0x34),
                defaultColour.setAlpha(0x00)).toIntArray()
    }

    private val stops = listOf(0.0f, 0.2f, 0.42f, 0.6f, 0.75f).toFloatArray()

    private val shaderFactory: (width: Float, height: Float) -> Shader = { viewWidth, viewHeight ->
        RadialGradient(
                viewWidth / 2f,
                viewHeight / 2f,
                Math.min(viewWidth, viewHeight) / 2f,
                colours,
                stops,
                Shader.TileMode.CLAMP
        )
    }

    private var buggyShaderFactory: (width: Float, height: Float) -> Shader = { viewWidth, viewHeight ->
        RadialGradient(
                viewWidth / 2f,
                viewHeight / 2f,
                Math.min(viewWidth, viewHeight) / 2f,
                defaultColour,
                Color.TRANSPARENT,
                Shader.TileMode.CLAMP
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dark_custom?.shaderFactory = shaderFactory
        dark_software?.shaderFactory = buggyShaderFactory
        light_custom?.shaderFactory = shaderFactory
        light_software?.shaderFactory = buggyShaderFactory
    }
}
