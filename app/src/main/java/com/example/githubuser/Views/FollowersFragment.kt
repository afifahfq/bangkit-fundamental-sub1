package com.example.githubuser.Views

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuser.Adapter.ListUserAdapter
import com.example.githubuser.Models.User
import com.example.githubuser.R
import com.example.githubuser.ViewModels.UserViewModel

class FollowersFragment : Fragment() {
    private lateinit var rvHeroes: RecyclerView
    private lateinit var mLiveDataList: UserViewModel
    private val list = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_followers, container, false)

        rvHeroes = view.findViewById(R.id.rv_followers)
        rvHeroes.setHasFixedSize(true)

        mLiveDataList = ViewModelProvider(this)[UserViewModel::class.java]
        subscribe()
        mLiveDataList.findUsers("sidiqpermana")

        return view
    }

    private fun subscribe() {
        val listObserver = Observer<ArrayList<User>?> { aList ->
            showRecyclerList(aList)
        }
        mLiveDataList.getList().observe(viewLifecycleOwner, listObserver)

        val statusObserver = Observer<Boolean> { aStatus ->
            //showLoading(aStatus)
        }
        mLiveDataList.getStatus().observe(viewLifecycleOwner, statusObserver)
    }

    private fun showRecyclerList(aList: ArrayList<User>) {
        rvHeroes.layoutManager = LinearLayoutManager(activity)

        val listUserAdapter = ListUserAdapter(aList)
        rvHeroes.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {

                val detailUserIntent = Intent(activity, DetailUserActivity::class.java)
                detailUserIntent.putExtra(DetailUserActivity.EXTRA_USER, data)
                startActivity(detailUserIntent)
            }
        })
    }

    companion object {

    }
}