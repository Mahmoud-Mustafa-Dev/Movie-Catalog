package com.mahmoud.network.catalog.catalog

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mahmoud.common.entities.Movie

class CatalogPagingSource(private val api: ICatalogApi): PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    //todo get type of movies to be received
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
            val response = api.getPopularMovies(nextPage)
            var nextPageNumber: Int? = null
            if(response.body()?.page != null) {
                nextPageNumber = response.body()!!.page + 1
            }
            LoadResult.Page(data = response.body()!!.movies, prevKey = null, nextKey = nextPageNumber)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }
}
