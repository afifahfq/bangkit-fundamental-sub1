package com.example.githubuser.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.Api.ApiConfig
import com.example.githubuser.Models.DetailResponse
import com.example.githubuser.Models.DetailUser
import com.example.githubuser.Models.SearchResponse
import com.example.githubuser.Models.User
import com.example.githubuser.Views.DetailUserActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {
    val detailViewModelStatus = MutableLiveData<Boolean>()
    val mDetailUser = MutableLiveData<DetailUser>()
    private lateinit var detailUser: DetailUser

    fun findUserDetail(username: String?){
        detailViewModelStatus.postValue(true)

        val client = ApiConfig.getApiService().getUser(username!!)
        client.enqueue(object : Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                val responseBody = response.body()
                if (responseBody != null) {
                    detailUser = DetailUser(
                        responseBody.login,
                        responseBody.name,
                        responseBody.location,
                        responseBody.publicRepos,
                        responseBody.company,
                        responseBody.followersUrl,
                        responseBody.followingUrl,
                        responseBody.avatarUrl
                    )
                    detailViewModelStatus.postValue(false)
                    mDetailUser.postValue(detailUser)
                }
            }
            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                Log.e("DetailUserActivity", "onFailure: ${t.message}")
            }
        })
    }

    fun getDetail(): MutableLiveData<DetailUser> {
        return mDetailUser
    }

    fun getStatus(): MutableLiveData<Boolean> {
        return detailViewModelStatus
    }
}