package com.example.githubuser.Views

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuser.Adapter.ListFavoriteAdapter
import com.example.githubuser.Adapter.ListUserAdapter
import com.example.githubuser.Database.Favorite
import com.example.githubuser.Database.FavoriteViewModel
import com.example.githubuser.Models.User
import com.example.githubuser.R
import com.example.githubuser.ViewModels.UserViewModel
import com.example.githubuser.databinding.ActivityFavoriteBinding
import com.example.githubuser.databinding.ActivityMainBinding

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var mLiveDataFavorite: FavoriteViewModel
    private lateinit var rvUsers: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle("Favorite Users")

        rvUsers = findViewById(R.id.rv_users)
        rvUsers.setHasFixedSize(true)

        mLiveDataFavorite = ViewModelProvider(this)[FavoriteViewModel::class.java]
        subscribe()
        mLiveDataFavorite.readAllData
    }

    private fun subscribe() {
        val listObserver = Observer<List<Favorite>?> { aList ->
            showRecyclerList(aList)
        }
        mLiveDataFavorite.readAllData.observe(this, listObserver)
    }

    private fun showRecyclerList(aList: List<Favorite>) {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rvUsers.layoutManager = GridLayoutManager(this, 2)
        } else {
            rvUsers.layoutManager = LinearLayoutManager(this)
        }

        val listFavoriteAdapter = ListFavoriteAdapter(aList)
        rvUsers.adapter = listFavoriteAdapter

        listFavoriteAdapter.setOnItemClickCallback(object : ListFavoriteAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Favorite) {

                Log.i("CEKHASIL", data.toString())

                val user = User(
                    data.username,
                    data.deskripsi,
                    data.avatar
                )

                val detailUserIntent = Intent(this@FavoriteActivity, DetailUserActivity::class.java)
                detailUserIntent.putExtra(DetailUserActivity.EXTRA_USER, user)
                startActivity(detailUserIntent)
            }
        })
    }
}