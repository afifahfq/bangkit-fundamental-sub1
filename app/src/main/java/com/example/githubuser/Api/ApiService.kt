package com.example.githubuser.Api

import com.example.githubuser.Models.DetailResponse
import com.example.githubuser.Models.FollowResponseItem
import com.example.githubuser.Models.SearchResponse
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    @GET("/search/users")
    //@Headers("Authorization: token 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")
    fun searchUsers(
        @Query("q") username: String
    ): Call<SearchResponse>

    @GET("users/{username}")
    //@Headers("Authorization: token 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")
    fun getUser(
        @Path("username") username: String
    ): Call<DetailResponse>

    @GET("users/{username}/followers")
    //@Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<List<FollowResponseItem>>

    @GET("users/{username}/following")
    //@Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<List<FollowResponseItem>>

}