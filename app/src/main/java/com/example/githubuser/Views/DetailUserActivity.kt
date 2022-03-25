package com.example.githubuser.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.githubuser.Api.ApiConfig
import com.example.githubuser.Models.DetailResponse
import com.example.githubuser.Models.DetailUser
import com.example.githubuser.R
import com.example.githubuser.Models.User
import com.example.githubuser.databinding.ActivityDetailUserBinding
import com.example.githubuser.databinding.ActivityDetailUserBinding.inflate
import retrofit2.*

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var detailUser: DetailUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_detail_user)
        setTitle("Detail User")

        val user = intent.getParcelableExtra<DetailUser>(EXTRA_USER) as User

        findUserDetail(user.username)
    }

    private fun findUserDetail(username: String?){
        val client = ApiConfig.getApiService().getUser(username!!)
        client.enqueue(object : Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                showLoading(false)
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
                    setText()
                }
            }
            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                showLoading(false)
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    private fun setText() {
        val imageObject: ImageView = findViewById(R.id.imageView)
        val nameObject: TextView = findViewById(R.id.name_object)
        val usernameObject: TextView = findViewById(R.id.username_object)
        val companyObject: TextView = findViewById(R.id.company_object)
        val locationObject: TextView = findViewById(R.id.location_object)
        val repositoryObject: TextView = findViewById(R.id.repository_object)

        Glide.with(imageObject.context)
            .load(detailUser.avatar)
            .circleCrop()
            .into(imageObject)

        val username = "${detailUser.username}"
        usernameObject.text = username

        val name = "${detailUser.name}"
        nameObject.text = name

        val company = "Company : ${detailUser.company}"
        companyObject.text = company

        val location = "Location : ${detailUser.location}"
        locationObject.text = location

        val repository = "Repository : ${detailUser.repository}"
        repositoryObject.text = repository
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        private const val TAG = "DetailUserActivity"
        const val EXTRA_USER = "extra_user"
    }

}