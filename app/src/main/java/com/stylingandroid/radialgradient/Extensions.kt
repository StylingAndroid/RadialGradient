package com.stylingandroid.radialgradient

import android.content.res.Resources
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

