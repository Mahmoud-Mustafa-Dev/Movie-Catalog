package com.mahmoud.network.catalog.catalog

import com.mahmoud.common.entities.Movie

interface ICatalogApi {
    suspend fun getPopularMovies(): List<Movie>
}
