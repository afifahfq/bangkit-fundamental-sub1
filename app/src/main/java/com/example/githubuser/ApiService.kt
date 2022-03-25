package com.example.githubuser

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("user/{username}")
    fun getRestaurant(
        @Path("username") id: String
    ): Call<ApiResponse>
}