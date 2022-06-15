package com.mahmoud.common.error

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import com.mahmoud.common.R

object DefaultErrorHandler {

    fun handleError(activity: Activity, error: Throwable) {
        val contentView = (activity.findViewById(android.R.id.content) as ViewGroup).getChildAt(0)
        handleError(contentView, error)
    }

    fun handleError(view: View, error: Throwable) {
        showSnackbar(view, R.string.general_error)
    }

    private fun showSnackbar(view: View, @StringRes message: Int) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }
}
