package com.mahmoud.use_case.catalog

import com.mahmoud.common.entities.Movie
import com.mahmoud.repository.catalog.CatalogRepository

class CatalogUseCase(
    private val repository: CatalogRepository
) {
     suspend fun getPopularMovies(): List<Movie> {
        return repository.getPopularMovies()
    }
}
