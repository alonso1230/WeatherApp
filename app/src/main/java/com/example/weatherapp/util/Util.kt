package com.example.weatherapp.util

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

object Util {

    fun hideKeyboard(activity: Activity) {
        val view = activity.currentFocus
        view?.let { v ->
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }
}