package com.mahmoud.common.extensions

import android.view.View

fun View.show() {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
}

fun View.hide() {
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
}