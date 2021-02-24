package com.r2omm.core.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.MenuRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

fun Fragment.hideKeyboard() {
    view?.windowToken?.let {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(it, 0)
    }
}

fun Fragment.setupActionBar(toolbar: Toolbar) {
    setHasOptionsMenu(true)
    val appCompatActivity = requireActivity() as AppCompatActivity
    appCompatActivity.setSupportActionBar(toolbar)
    appCompatActivity.setupActionBarWithNavController(findNavController())
}

fun Fragment.makeToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Fragment.makeToast(@StringRes message: Int) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Fragment.setupToolbarMenu(
    toolbar: Toolbar,
    @MenuRes menu: Int,
    onMenuItemClick: (itemId: Int) -> Unit
) {
    setHasOptionsMenu(true)
    toolbar.setupWithNavController(findNavController())
    toolbar.inflateMenu(menu)
    toolbar.setOnMenuItemClickListener {
        onMenuItemClick(it.itemId)
        true
    }
}

fun Fragment.goToUrl(url: String) {
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    startActivity(browserIntent)
}

