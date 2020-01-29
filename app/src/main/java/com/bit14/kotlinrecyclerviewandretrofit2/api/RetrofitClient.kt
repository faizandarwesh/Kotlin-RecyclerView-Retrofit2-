package com.bit14.kotlinrecyclerviewandretrofit2.api

import com.bit14.kotlinrecyclerviewandretrofit2.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val instance : WebServices by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.POST_URL)
            // Gson maps JSON to classes
            .addConverterFactory(GsonConverterFactory.create())
            // The call adapter handles threads
            .build()

        // Create Retrofit client
        return@lazy retrofit.create(WebServices::class.java)
    }
}