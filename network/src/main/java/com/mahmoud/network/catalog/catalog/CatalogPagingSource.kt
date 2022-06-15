package com.mahmoud.network.catalog.catalog

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mahmoud.common.entities.Movie
import com.mahmoud.common.entities.MoviesType

class CatalogPagingSource(private val api: ICatalogApi, private val moviesType: MoviesType): PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPage: Int = params.key ?: moviesType.initialIndex
            val response =
            when(moviesType) {
                is MoviesType.PopularMoviesType -> api.getPopularMovies(nextPage)
                is MoviesType.TopRatedMoviesType -> api.getTopRatedMovies(nextPage)
                is MoviesType.RevenueMoviesType -> api.getRevenueMovies(nextPage)
            }

            var nextPageNumber: Int? = null
            if(response.body()?.page != null) {
                nextPageNumber = response.body()!!.page + 1
            }
            LoadResult.Page(data = response.body()!!.movies, prevKey = null, nextKey = nextPageNumber)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
