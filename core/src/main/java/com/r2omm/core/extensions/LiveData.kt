package com.r2omm.core.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.r2omm.core.event.Event

fun <T> LiveData<T>.valueOrThrow() = this.value!!

fun <T> MutableLiveData<Event<T>>.postEvent(value: T) = postValue(Event(value))