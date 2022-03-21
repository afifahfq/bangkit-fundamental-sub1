package com.example.githubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import com.bumptech.glide.Glide

class DetailUser : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)
        setTitle("Detail User");

        val imageObject: ImageView = findViewById(R.id.imageView)
        val nameObject: TextView = findViewById(R.id.name_object)
        val usernameObject: TextView = findViewById(R.id.username_object)
        val companyObject: TextView = findViewById(R.id.company_object)
        val locationObject: TextView = findViewById(R.id.location_object)

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User

        Glide.with(imageObject.getContext())
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
    }
}