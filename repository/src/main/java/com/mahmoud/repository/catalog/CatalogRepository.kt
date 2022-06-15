package com.mahmoud.repository.catalog

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mahmoud.common.entities.Movie
import com.mahmoud.common.entities.MoviesType
import com.mahmoud.network.catalog.catalog.CatalogPagingSource
import com.mahmoud.network.catalog.catalog.ICatalogApi
import kotlinx.coroutines.flow.Flow

class CatalogRepository(private val api: ICatalogApi) {
    //todo create the paging config prior
    fun getMovies(moviesType: MoviesType): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = moviesType.pagingConfig.pageSize),
            pagingSourceFactory = { CatalogPagingSource(api, moviesType) }).flow
    }
}
