package com.mahmoud.network.catalog.catalog

import com.mahmoud.common.entities.Movie

class CatalogApi: ICatalogApi {
    override suspend fun getPopularMovies(): List<Movie> {
        return listOf(Movie(title = "Paprika"))
    }
}
