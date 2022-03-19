package com.example.githubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailUser : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        val tvObject: TextView = findViewById(R.id.tv_object_received)

        val user = intent.getParcelableExtra<User2>(EXTRA_USER) as User2
        val text = "Username : ${user.username},\nName : ${user.name},\nLocation : ${user.location},\nRepository : ${user.repository}"
        tvObject.text = text
    }
}