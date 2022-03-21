package com.example.githubuser

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvHeroes: RecyclerView
    private val list = ArrayList<User2>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvHeroes = findViewById(R.id.rv_heroes)
        rvHeroes.setHasFixedSize(true)

        list.addAll(listUsers)
        showRecyclerList()
    }

    private val listUsers: ArrayList<User2>
        get() {
            val dataUsername = resources.getStringArray(R.array.username)
            val dataName = resources.getStringArray(R.array.name)
            val dataLocation = resources.getStringArray(R.array.location)
            val dataRepository = resources.obtainTypedArray(R.array.repository)
            val dataCompany = resources.getStringArray(R.array.company)
            val dataFollowers = resources.obtainTypedArray(R.array.followers)
            val dataFollowing = resources.obtainTypedArray(R.array.following)
            val dataAvatar = resources.obtainTypedArray(R.array.avatar)
            val listHero = ArrayList<User2>()
            for (i in dataName.indices) {
                val hero = User2(dataUsername[i], dataName[i], dataLocation[i], dataRepository.getResourceId(i, -1), dataCompany[i], dataFollowers.getResourceId(i, -1),  dataFollowing.getResourceId(i, -1), dataAvatar.getResourceId(i, -1))
                listHero.add(hero)
        }
        return listHero
    }

    private fun showRecyclerList() {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rvHeroes.layoutManager = GridLayoutManager(this, 2)
        } else {
            rvHeroes.layoutManager = LinearLayoutManager(this)
        }

        val listHeroAdapter = ListUserAdapter(list)
        rvHeroes.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User2) {
                val user = User2(
                    "DicodingAcademy",
                    "name",
                    "location",
                    5,
                    "academy@dicoding.com",
                    33,
                    44,
                    1
                )

                //val datauser = User2(data)

                val DetailUserIntent = Intent(this@MainActivity, DetailUser::class.java)
                DetailUserIntent.putExtra(DetailUser.EXTRA_USER, data)
                startActivity(DetailUserIntent)
            }
        })
    }

    private fun showSelectedHero(user: User2) {
        Toast.makeText(this, "Kamu memilih " + user.name, Toast.LENGTH_SHORT).show()
    }
}