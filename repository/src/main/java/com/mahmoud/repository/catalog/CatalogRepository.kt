package com.mahmoud.repository.catalog

import com.mahmoud.common.entities.Movie
import com.mahmoud.network.catalog.catalog.ICatalogApi

class CatalogRepository(private val api: ICatalogApi) {
    suspend fun getPopularMovies(): List<Movie> {
        return api.getPopularMovies()
    }
}
