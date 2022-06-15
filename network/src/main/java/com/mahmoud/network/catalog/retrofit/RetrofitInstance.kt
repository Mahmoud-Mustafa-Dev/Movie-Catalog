package com.mahmoud.network.catalog.retrofit

import com.mahmoud.network.catalog.catalog.ICatalogApi
import com.mahmoud.network.catalog.retrofit.RetrofitConstants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val api: ICatalogApi by lazy {
            retrofit.create(ICatalogApi::class.java)
        }
    }
}
