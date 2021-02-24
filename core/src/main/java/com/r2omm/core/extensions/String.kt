package com.r2omm.core.extensions

fun String?.asInt(): Int {
    return try {
        this!!.trimStart('0').toInt()
    } catch (e: Exception) {
        0
    }
}