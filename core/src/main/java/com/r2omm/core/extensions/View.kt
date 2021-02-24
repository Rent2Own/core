package com.r2omm.core.extensions

import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar

fun EditText.setupDatePicker(
    fragmentManager: FragmentManager,
    selectionDate: Long = MaterialDatePicker.todayInUtcMilliseconds(),
    onDatePick: (millSec: Long) -> Unit
): MaterialDatePicker<Long> {
    val builder = MaterialDatePicker.Builder.datePicker()
    builder.setSelection(selectionDate)
        .setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR)
    val picker = builder.build()
    setOnClickListener {
        picker.show(fragmentManager, picker.toString())
    }
    picker.addOnPositiveButtonClickListener {
        setText(picker.headerText)
        onDatePick.invoke(it)
    }
    return picker
}

fun View.showSnackbar(
    msg: String,
    length: Int,
    actionMessage: CharSequence?,
    action: () -> Unit
) {
    val snackbar = Snackbar.make(this, msg, length)
    if (actionMessage != null) {
        snackbar.setAction(actionMessage) {
            action()
        }.show()
    }
}

fun View.showSnackbar(
    @StringRes msg: Int,
    length: Int,
    @StringRes actionMessage: Int?,
    action: () -> Unit
) {
    val snackbar = Snackbar.make(this, msg, length)
    if (actionMessage != null) {
        snackbar.setAction(actionMessage) {
            action()
        }.show()
    }
}

fun View.setOnClickListenerThrottled(action: () -> Unit) {
    val throttleTime = 500L
    setOnClickListener(object : View.OnClickListener {
        var lastClickTime = 0L
        override fun onClick(p0: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < throttleTime) {
                return
            } else action()
            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}
