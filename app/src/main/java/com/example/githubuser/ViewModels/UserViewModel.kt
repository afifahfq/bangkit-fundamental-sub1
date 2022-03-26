package com.example.githubuser.ViewModels

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.Api.ApiConfig
import com.example.githubuser.Models.SearchResponse
import com.example.githubuser.Models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel: ViewModel() {
    val userViewModelStatus = MutableLiveData<Int>()
    val mList = MutableLiveData<ArrayList<User>>()
    val list = ArrayList<User>()

    fun findUsers(username: String) {
        list.clear()
        mList.postValue(list)

        userViewModelStatus.value = 1
        val client = ApiConfig.getApiService().searchUsers(username)
        client.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                val responseBody = response.body()
                if (responseBody != null) {
                    for (user in responseBody.items!!) {
                        var curr = User(
                            user?.login,
                            user?.htmlUrl,
                            user?.avatarUrl
                        )
                        list.add(curr)
                    }
                    Log.i("cekList", list.toString())
                    mList.postValue(list)
                }
            }
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.e("UserViewModel", "onFailure: ${t.message}")
            }
        })
    }

    fun getList(): LiveData<ArrayList<User>?> {
        return mList
    }
}