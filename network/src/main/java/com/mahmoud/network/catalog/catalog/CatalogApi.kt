package com.mahmoud.network.catalog.catalog

import com.mahmoud.network.catalog.retrofit.RetrofitInstance
import retrofit2.Response

class CatalogApi: ICatalogApi {
    override suspend fun getPopularMovies(page: Int): Response<MoviesResponse> {
        return RetrofitInstance.api.getPopularMovies(page)
    }

    override suspend fun getTopRatedMovies(page: Int): Response<MoviesResponse> {
        return RetrofitInstance.api.getTopRatedMovies(page)
    }

    override suspend fun getRevenueMovies(page: Int): Response<MoviesResponse> {
        return RetrofitInstance.api.getRevenueMovies(page)
    }
}
