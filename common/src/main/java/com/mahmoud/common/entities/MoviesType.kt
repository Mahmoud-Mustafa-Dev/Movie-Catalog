package com.mahmoud.common.entities

import androidx.paging.PagingConfig

sealed class MoviesType(
    loadSize: Int = DEFAULT_PAGE_SIZE,
    val initialIndex: Int = FIRST_PAGE_INDEX
) {
    var pagingConfig: PagingConfig = PagingConfig(loadSize, enablePlaceholders = true)

    object PopularMoviesType: MoviesType()
    object TopRatedMoviesType: MoviesType()
    object RevenueMoviesType: MoviesType()


    companion object {
        const val FIRST_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 20
    }
}
