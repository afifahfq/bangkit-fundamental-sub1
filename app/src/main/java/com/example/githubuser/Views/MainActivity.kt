package com.example.githubuser.Views

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
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuser.*
import com.example.githubuser.Adapter.ListUserAdapter
import com.example.githubuser.Models.User
import com.example.githubuser.ViewModels.UserViewModel
import com.example.githubuser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mLiveDataList: UserViewModel
    private lateinit var rvUsers: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvUsers = findViewById(R.id.rv_users)
        rvUsers.setHasFixedSize(true)

        mLiveDataList = ViewModelProvider(this)[UserViewModel::class.java]
        subscribe()
        mLiveDataList.findUsers(USER_USERNAME)
    }

    private fun subscribe() {
        val listObserver = Observer<ArrayList<User>?> { aList ->
            showRecyclerList(aList)
        }
        mLiveDataList.getList().observe(this, listObserver)

        val statusObserver = Observer<Boolean> { aStatus ->
            showLoading(aStatus)
        }
        mLiveDataList.getStatus().observe(this, statusObserver)
    }

    private fun showRecyclerList(aList: ArrayList<User>) {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rvUsers.layoutManager = GridLayoutManager(this, 2)
        } else {
            rvUsers.layoutManager = LinearLayoutManager(this)
        }

        val listUserAdapter = ListUserAdapter(aList)
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                //Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
                mLiveDataList.findUsers(query)
                searchView.clearFocus()
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu1 -> {
                startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
                return true
            }
            else -> return true
        }
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val USER_USERNAME = "sidiqpermana"
    }
}