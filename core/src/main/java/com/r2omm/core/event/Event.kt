package com.r2omm.core.event

class Event<out T>(private val content: T) {
    private var handled: Boolean = false

    fun getContentIfNotHandled(): T? {
        return if (handled) {
            null
        } else {
            handled = true
            content
        }
    }
}