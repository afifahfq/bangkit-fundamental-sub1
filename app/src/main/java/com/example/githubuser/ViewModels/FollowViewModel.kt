package com.example.githubuser.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.Api.ApiConfig
import com.example.githubuser.Models.FollowResponseItem
import com.example.githubuser.Models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowViewModel: ViewModel() {
    val userViewModelStatus = MutableLiveData<Boolean>()
    val mFollowers = MutableLiveData<ArrayList<User>>()
    val followers = ArrayList<User>()

    val mFollowing = MutableLiveData<ArrayList<User>>()
    val following = ArrayList<User>()

    fun findFollowers(username: String) {
        followers.clear()
        mFollowers.postValue(followers)

        userViewModelStatus.postValue(true)
        val client = ApiConfig.getApiService().getFollowers(username)
        client.enqueue(object : Callback<List<FollowResponseItem>> {
            override fun onResponse(
                call: Call<List<FollowResponseItem>>,
                response: Response<List<FollowResponseItem>>
            ) {
                Log.i("CEKPOIN", "MASUK")
                val responseBody = response.body()
                if (responseBody != null) {
                    for (user in responseBody) {
                        var curr = User(
                            user?.login,
                            user?.htmlUrl,
                            user?.avatarUrl
                        )
                        followers.add(curr)
                    }
                }
                userViewModelStatus.postValue(false)
                mFollowers.postValue(followers)
            }
            override fun onFailure(call: Call<List<FollowResponseItem>>, t: Throwable) {
            Log.e("FollowViewModel", "onFailure: ${t.message}")
            }
        })
    }

    fun findFollowing(username: String) {
        following.clear()
        mFollowing.postValue(following)

        userViewModelStatus.postValue(true)
        val client = ApiConfig.getApiService().getFollowing(username)
        client.enqueue(object : Callback<List<FollowResponseItem>> {
            override fun onResponse(
                call: Call<List<FollowResponseItem>>,
                response: Response<List<FollowResponseItem>>
            ) {
                Log.i("CEKPOIN", "MASUK")
                val responseBody = response.body()
                if (responseBody != null) {
                    for (user in responseBody) {
                        var curr = User(
                            user?.login,
                            user?.htmlUrl,
                            user?.avatarUrl
                        )
                        following.add(curr)
                    }
                }
                userViewModelStatus.postValue(false)
                mFollowing.postValue(following)
            }
            override fun onFailure(call: Call<List<FollowResponseItem>>, t: Throwable) {
                Log.e("FollowViewModel", "onFailure: ${t.message}")
            }
        })
    }

    fun getFollowing(): LiveData<ArrayList<User>?> {
        return mFollowing
    }

    fun getFollowers(): LiveData<ArrayList<User>?> {
        return mFollowers
    }

    fun getStatus(): MutableLiveData<Boolean> {
        return userViewModelStatus
    }
}