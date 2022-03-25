package com.example.githubuser

import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    @GET("/search/users")
    @Headers("Authorization: token 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")
    fun searchUsers(
        @Query("q") username: String
    ): Call<SearchResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUser(
        @Path("username") username: String
    ): Call<DetailResponse>

}




//    @Query("search/users")
//    @Headers("Authorization: token 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")
//    Call<SearchResponse> fun searchUsers(
//        @Query("username") String q
//    )