package com.example.githubuser.Adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.githubuser.Views.DetailUserActivity
import com.example.githubuser.Views.FollowersFragment
import com.example.githubuser.Views.FollowingFragment
import com.example.githubuser.Views.TabbedFragment

class SectionsPagerAdapter(fragment: TabbedFragment) : FragmentStateAdapter(fragment)  {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowersFragment()
            1 -> fragment = FollowingFragment()
        }
        return fragment as Fragment
    }
}