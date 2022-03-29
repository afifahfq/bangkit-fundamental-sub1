package com.example.githubuser.Views

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuser.Adapter.ListUserAdapter
import com.example.githubuser.Models.User
import com.example.githubuser.R
import com.example.githubuser.ViewModels.FollowViewModel
import com.example.githubuser.databinding.FragmentFollowBinding
import kotlin.properties.Delegates

class FollowFragment : Fragment() {
    private lateinit var mLiveDataList: FollowViewModel
    private lateinit var rvUsers: RecyclerView
    private lateinit var username: String
    private var index by Delegates.notNull<Int>()

    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View =  inflater.inflate(R.layout.fragment_follow, container, false)
        _binding = FragmentFollowBinding.inflate(inflater, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvUsers = view.findViewById(R.id.rv_users)
        rvUsers.setHasFixedSize(true)

        username = arguments?.getString(ARG_USERNAME)!!
        index = requireArguments().getInt(ARG_SECTION_NUMBER, 0)

        mLiveDataList = ViewModelProvider(this)[FollowViewModel::class.java]
        subscribe(index)
        if (index == 2) {
            mLiveDataList.findFollowing(username)
        } else {
            mLiveDataList.findFollowers(username)
        }

    }

    private fun subscribe(index: Int) {
        val statusObserver = Observer<Boolean> { aStatus ->
            showLoading(aStatus)
        }
        mLiveDataList.getStatus().observe(viewLifecycleOwner, statusObserver)

        if (index == 1) {
            val followersObserver = Observer<ArrayList<User>?> { aList ->
                showRecyclerList(aList)
            }
            mLiveDataList.getFollowers().observe(viewLifecycleOwner , followersObserver)
        } else {
            val followingObserver = Observer<ArrayList<User>?> { aList ->
                showRecyclerList(aList)
            }
            mLiveDataList.getFollowing().observe(viewLifecycleOwner , followingObserver)
        }
    }

    private fun showRecyclerList(aList: ArrayList<User>) {
        rvUsers.layoutManager = LinearLayoutManager(activity)

        val listUserAdapter = ListUserAdapter(aList)
        rvUsers.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
            binding.rvUsers.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.rvUsers.visibility = View.VISIBLE
        }
    }

    companion object {
        const val ARG_SECTION_NUMBER = "section_number"
        const val ARG_USERNAME = "afifahfq"
    }
}