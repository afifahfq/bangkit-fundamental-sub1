package com.example.githubuser

import retrofit2.Call
import retrofit2.http.*


interface ApiService {
    @GET("users")
    @Headers("Authorization: token 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")
    fun getAllUsers(): Call<ApiResponse>

    @GET("/search/users")
    @Headers("Authorization: token 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")
    fun searchUser(
        @Query("q") username: String
    ): Call<ApiResponse>

}




//    @Query("search/users")
//    @Headers("Authorization: token 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")
//    Call<ApiResponse> fun searchUser(
//        @Query("username") String q
//    )