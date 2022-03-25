package com.example.githubuser

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuser.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var rvUsers: RecyclerView
    private val list = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)

        rvUsers = findViewById(R.id.rv_users)
        rvUsers.setHasFixedSize(true)

        findUsers()
        //Log.i("cekpoin", "HA")
    }

//    private val listUsers: ArrayList<User>
//        get() {
//            val dataUsername = resources.getStringArray(R.array.username)
//            val dataName = resources.getStringArray(R.array.name)
//            val dataLocation = resources.getStringArray(R.array.location)
//            val dataRepository = resources.getStringArray(R.array.repository)
//            val dataCompany = resources.getStringArray(R.array.company)
//            val dataFollowers = resources.getStringArray(R.array.followers)
//            val dataFollowing = resources.getStringArray(R.array.following)
//            val dataAvatar = resources.getStringArray(R.array.avatar)
//            //val dataAvatar = resources.obtainTypedArray(R.array.avatar)
//            val listUser = ArrayList<User>()
//            for (i in dataName.indices) {
//                val user = User(dataUsername[i], dataName[i], dataLocation[i], dataRepository[i], dataCompany[i], dataFollowers[i],  dataFollowing[i], dataAvatar[i])
//                //val user = User(dataUsername[i], dataName[i], dataLocation[i], dataRepository[i], dataCompany[i], dataFollowers[i],  dataFollowing[i], dataAvatar.getResourceId(i, -1))
//                listUser.add(user)
//            }
//        Log.i("cekListUser", listUser.toString())
//        return listUser
//    }

    private fun findUsers() {
        showLoading(true)
        val client = ApiConfig.getApiService().searchUser(USER_USERNAME)
        client.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(
                call: Call<ApiResponse>,
                response: Response<ApiResponse>
            ) {
                showLoading(false)
                val responseBody = response.body()
                if (responseBody != null) {
                    for (user in responseBody.items!!) {
                        // ini baru asal assign saja yg penting tiap attribute di class User terisi
                        var curr = User(
                            user?.login,
                            user?.htmlUrl,
                            user?.htmlUrl,
                            user?.reposUrl,
                            user?.htmlUrl,
                            user?.followersUrl,
                            user?.followingUrl,
                            user?.avatarUrl )
                        list.add(curr)
                    }
                    Log.i("cekList", list.toString())
                    showRecyclerList()
                }
            }
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                showLoading(false)
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

//    private fun findUser() {
//        showLoading(true)
//        val client = ApiConfig.getApiService().getUser(USER_USERNAME)
//        client.enqueue(object : Callback<ApiResponse> {
//            override fun onResponse(
//                call: Call<ApiResponse>,
//                response: Response<ApiResponse>
//            ) {
//                showLoading(false)
//                val responseBody = response.body()
//                if (responseBody != null) {
//                    for (user in responseBody.ApiResponse!!) {
//                        var curr = User(
//                            user?.login,
//                            user?.htmlUrl,
//                            user?.htmlUrl,
//                            user?.reposUrl,
//                            user?.htmlUrl,
//                            user?.followersUrl,
//                            user?.followingUrl,
//                            user?.avatarUrl )
//
//                        list.add(curr)
//                    }
//
//                    //list.addAll(listUsers)
//                    //showRecyclerList()
//                }
//            }
//            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
//                showLoading(false)
//                Log.e(TAG, "onFailure: ${t.message}")
//            }
//        })
//    }

    private fun showRecyclerList() {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rvUsers.layoutManager = GridLayoutManager(this, 2)
        } else {
            rvUsers.layoutManager = LinearLayoutManager(this)
        }

        val listUserAdapter = ListUserAdapter(list)
        rvUsers.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {

                val detailUserIntent = Intent(this@MainActivity, DetailUserActivity::class.java)
                detailUserIntent.putExtra(DetailUserActivity.EXTRA_USER, data)
                startActivity(detailUserIntent)
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        val inflater = menuInflater
//        inflater.inflate(R.menu.option_menu, menu)
//
//        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        val searchView = menu.findItem(R.id.search).actionView as SearchView
//
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
//        searchView.queryHint = resources.getString(R.string.search_hint)
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String): Boolean {
//                Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
//                searchView.clearFocus()
//                return true
//            }
//            override fun onQueryTextChange(newText: String): Boolean {
//                return false
//            }
//        })
//
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.menu1 -> {
//                startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
//                return true
//            }
//            else -> return true
//        }
//    }

    companion object {
        private const val TAG = "MainActivity"
        private const val USER_USERNAME = "sidiqpermana"
    }
}