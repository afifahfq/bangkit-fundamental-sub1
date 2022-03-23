package com.example.githubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)
        setTitle("Detail User")

        val imageObject: ImageView = findViewById(R.id.imageView)
        val nameObject: TextView = findViewById(R.id.name_object)
        val usernameObject: TextView = findViewById(R.id.username_object)
        val companyObject: TextView = findViewById(R.id.company_object)
        val locationObject: TextView = findViewById(R.id.location_object)
        val repositoryObject: TextView = findViewById(R.id.repository_object)
        val followersObject: TextView = findViewById(R.id.follower_object)
        val followingObject: TextView = findViewById(R.id.following_object)

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User

        Glide.with(imageObject.context)
            .load(user.avatar)
            .circleCrop()
            .into(imageObject)

        val username = "${user.username}"
        usernameObject.text = username

        val name = "${user.name}"
        nameObject.text = name

        val company = "Company : ${user.company}"
        companyObject.text = company

        val location = "Location : ${user.location}"
        locationObject.text = location

        val repository = "Repository : ${user.repository}"
        repositoryObject.text = repository

        val followers = "${user.followers}"
        followersObject.text = followers

        val following = "${user.following}"
        followingObject.text = following
    }

    companion object {
        const val EXTRA_USER = "extra_user"
    }

}