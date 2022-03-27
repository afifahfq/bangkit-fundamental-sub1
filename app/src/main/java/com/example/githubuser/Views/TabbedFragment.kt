package com.example.githubuser.Views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.githubuser.Adapter.SectionsPagerAdapter
import com.example.githubuser.Models.DetailUser
import com.example.githubuser.Models.User
import com.example.githubuser.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class TabbedFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_tabbed, container, false)

        // get username from DetailUserActivity
        val bundle = arguments
        val message = bundle!!.getString("mText")
        Log.i("CEKMESSSAGE", message.toString())

        // connect with pager adapter
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = view.findViewById(R.id.view_pager)

        // send username to follow fragment
        sectionsPagerAdapter.username = message!!

        // Inflate the layout for this fragment
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = view.findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        activity?.actionBar?.elevation = 0f

        return view
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

}