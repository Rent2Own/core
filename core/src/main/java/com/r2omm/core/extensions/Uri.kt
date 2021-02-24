package com.r2omm.core.extensions

import android.content.ContentResolver
import android.net.Uri
import android.provider.OpenableColumns
import com.r2omm.core.InputStreamRequestBody
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import java.util.*

fun Uri.getFileName(contentResolver: ContentResolver) =
    contentResolver.query(this, null, null, null, null)?.use {
        val index = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        it.moveToFirst()
        return@use it.getString(index)
    }

fun Uri.toFormData(partName: String, contentResolver: ContentResolver): MultipartBody.Part {
    val fileName = getFileName(contentResolver) ?: UUID.randomUUID().toString()
    val mime = (contentResolver.getType(this) ?: "image/jpeg").toMediaType()
    return MultipartBody.Part.createFormData(
        partName,
        fileName,
        InputStreamRequestBody(mime, contentResolver, this)
    )
}

