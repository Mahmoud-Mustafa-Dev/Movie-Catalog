package com.mahmoud.network.catalog.catalog

import com.google.gson.annotations.SerializedName
import com.mahmoud.common.entities.Movie

data class MoviesResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movies: List<Movie>,
    @SerializedName("total_pages") val pages: Int
)

