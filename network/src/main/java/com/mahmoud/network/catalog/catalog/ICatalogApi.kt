package com.mahmoud.network.catalog.catalog

import com.mahmoud.network.catalog.retrofit.RetrofitConstants.POPULAR_MOVIES
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ICatalogApi {
    @GET(POPULAR_MOVIES)
    suspend fun getPopularMovies(
      @Query("page")  page: Int
    ): Response<MoviesResponse>
}
