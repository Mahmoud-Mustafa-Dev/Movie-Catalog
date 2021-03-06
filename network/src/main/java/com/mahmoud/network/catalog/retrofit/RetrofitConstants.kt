package com.mahmoud.network.catalog.retrofit

object RetrofitConstants {
    const val BASE_URL = "https://api.themoviedb.org/3/discover/"
    const val API_KEY = "114fe6670282f6a632638661e5e86dee"
    const val CONTENT_TYPE = "application/json"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original/"

    const val POPULAR_MOVIES = "movie?api_key=114fe6670282f6a632638661e5e86dee&sort_by=popularity.desc"
    const val TOP_RATED_MOVIES = "movie?api_key=114fe6670282f6a632638661e5e86dee&sort_by=vote_average.desc"
    const val REVENUE_MOVIES = "movie?api_key=114fe6670282f6a632638661e5e86dee&sort_by=revenue.desc"
}
