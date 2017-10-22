package com.stylingandroid.radialgradient

import android.content.res.Resources
import android.graphics.Color
import android.support.annotation.ColorInt
import android.util.TypedValue

@ColorInt
fun Resources.Theme.getColour(id: Int): Int =
        TypedValue()
                .also { resolveAttribute(id, it, true) }
                .data

fun <T> lazyFast(operation: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE) {
    operation()
}

@ColorInt fun Int.setAlpha(alpha: Int): Int =
        Color.argb(alpha, Color.red(this), Color.green(this), Color.blue(this))
