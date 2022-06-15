package com.mahmoud.common.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var original_title: String,
    var poster_path: String,
    var overview: String,
    var vote_average: Float
) :
    Parcelable

