package com.mahmoud.use_case.catalog

import androidx.paging.PagingData
import com.mahmoud.common.entities.Movie
import com.mahmoud.common.entities.MoviesType
import com.mahmoud.repository.catalog.CatalogRepository
import kotlinx.coroutines.flow.Flow

class CatalogUseCase(
    private val repository: CatalogRepository
) {
      fun getMovies(moviesType: MoviesType): Flow<PagingData<Movie>> {
        return repository.getMovies(moviesType)
    }
}
