package com.mahmoud.network.catalog.catalog

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mahmoud.common.entities.Movie
import com.mahmoud.common.entities.MoviesFeed

class CatalogPagingSource(private val api: ICatalogApi, private val moviesFeed: MoviesFeed): PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPage: Int = params.key ?: moviesFeed.initialIndex
            //todo change the api calls
            val response =
            when(moviesFeed) {
                is MoviesFeed.PopularMoviesFeed -> api.getPopularMovies(nextPage)
                is MoviesFeed.TopRatedMoviesFeed -> api.getPopularMovies(nextPage)
                is MoviesFeed.RevenueMoviesFeed -> api.getPopularMovies(nextPage)
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
