package com.example.githubuser.Adapter

import android.os.Bundle
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

class SectionsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment)  {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = FollowersFragment()
        fragment.arguments = Bundle().apply {
            putInt(FollowersFragment.ARG_SECTION_NUMBER, position + 1)
        }
        return fragment
    }

}