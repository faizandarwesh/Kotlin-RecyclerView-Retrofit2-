package com.bit14.kotlinrecyclerviewandretrofit2.api

import com.bit14.kotlinrecyclerviewandretrofit2.models.Post
import retrofit2.Call
import retrofit2.http.GET

interface WebServices {

    @GET("posts")
    fun getPosts(): Call<List<Post>>
}