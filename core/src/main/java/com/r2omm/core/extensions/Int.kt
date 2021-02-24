package com.r2omm.core.extensions

import java.text.NumberFormat
import java.util.*

fun Int.toLocaleString(locale: Locale = Locale.getDefault()): String {
    val numberFormatter = NumberFormat.getNumberInstance(locale)
    return numberFormatter.format(this)
}