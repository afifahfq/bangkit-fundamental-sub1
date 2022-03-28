package com.example.githubuser.Adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.githubuser.Views.FollowFragment
import com.example.githubuser.Views.FollowersFragment
import com.example.githubuser.Views.FollowingFragment

class SectionsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment)  {
    var username: String = ""

//    override fun createFragment(position: Int): Fragment {
//        var fragment: Fragment? = null
//        when (position) {
//            0 -> fragment = FollowersFragment()
//            1 -> fragment = FollowingFragment()
//        }
//        return fragment as Fragment
//    }

    override fun createFragment(position: Int): Fragment {
        val fragment = FollowFragment()
        fragment.arguments = Bundle().apply {
            putString(FollowFragment.ARG_USERNAME, username)
            putInt(FollowFragment.ARG_SECTION_NUMBER, position + 1)
        }
        return fragment
    }

    override fun getItemCount(): Int {
        return 2
    }
}