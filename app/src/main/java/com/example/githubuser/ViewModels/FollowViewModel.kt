package com.example.githubuser.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.Api.ApiConfig
import com.example.githubuser.Models.FollowResponse
import com.example.githubuser.Models.ResponseItem
import com.example.githubuser.Models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowViewModel: ViewModel() {
    val userViewModelStatus = MutableLiveData<Boolean>()
    val mList = MutableLiveData<ArrayList<User>>()
    val list = ArrayList<User>()

    fun findFollowers(username: String) {
        list.clear()
        mList.postValue(list)

        userViewModelStatus.postValue(true)
        val client = ApiConfig.getApiService().getFollowers(username)
        client.enqueue(object : Callback<FollowResponse> {
            override fun onResponse(
                call: Call<FollowResponse>,
                response: Response<FollowResponse>
            ) {
                Log.i("CEKPOIN", "MASUK")
                val responseBody = response.body()
                for (user in responseBody?.response!!) {
                    var curr = User(
                        user?.login,
                        user?.htmlUrl,
                        user?.avatarUrl
                    )
                    list.add(curr)
                }
                userViewModelStatus.postValue(false)
                mList.postValue(list)
            }
            override fun onFailure(call: Call<FollowResponse>, t: Throwable) {
            Log.e("FollowViewModel", "onFailure: ${t.message}")
            }
        })
    }

    fun getList(): LiveData<ArrayList<User>?> {
        return mList
    }

    fun getStatus(): MutableLiveData<Boolean> {
        return userViewModelStatus
    }
}