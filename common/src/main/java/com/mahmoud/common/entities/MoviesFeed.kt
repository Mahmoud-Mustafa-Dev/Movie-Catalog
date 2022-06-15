package com.mahmoud.common.entities

import androidx.paging.PagingConfig

sealed class MoviesFeed(
    loadSize: Int = DEFAULT_PAGE_SIZE,
    val initialIndex: Int = FIRST_PAGE_INDEX
) {
    var pagingConfig: PagingConfig = PagingConfig(loadSize, enablePlaceholders = true)
    //todo add the rest of the feeds
    object PopularMoviesFeed: MoviesFeed()
    object TopRatedMoviesFeed: MoviesFeed()
    object RevenueMoviesFeed: MoviesFeed()


    companion object {
        const val FIRST_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 20
    }
}
