package com.mahmoud.moviecatalog.extensions

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController

fun Fragment.navigate(action: NavDirections) {
        requireView().findNavController().navigate(action)
}
