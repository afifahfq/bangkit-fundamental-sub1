package com.example.githubuser.Views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.githubuser.Models.DetailUser
import com.example.githubuser.Models.User
import com.example.githubuser.R
import com.example.githubuser.ViewModels.DetailViewModel
import com.example.githubuser.databinding.ActivityDetailUserBinding
import com.example.githubuser.databinding.ActivityDetailUserBinding.inflate

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var mLiveDetailUser: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_detail_user)
        setTitle("Detail User")

        mLiveDetailUser = ViewModelProvider(this)[DetailViewModel::class.java]
        subscribe()

        val user = intent.getParcelableExtra<DetailUser>(EXTRA_USER) as User
        mLiveDetailUser.findUserDetail(user.username)

//        val detailUserIntent = Intent(this, DetailUserActivity::class.java)
//        detailUserIntent.putExtra(DetailUserActivity.EXTRA_USER, user.username)

        val mFragmentManager = supportFragmentManager
        val mTabbedFragment = TabbedFragment()
        val mFragmentTransaction = mFragmentManager.beginTransaction()

        val mBundle = Bundle()
        mBundle.putString("mText", user.username)
        mTabbedFragment.arguments = mBundle
        mFragmentTransaction.commit()

        val fragment = mFragmentManager.findFragmentByTag(TabbedFragment::class.java.simpleName)
        if (fragment !is TabbedFragment) {
            Log.d("MyFlexibleFragment", "Fragment Name :" + TabbedFragment::class.java.simpleName)
            mFragmentManager
                .beginTransaction()
                .add(R.id.frame_container, mTabbedFragment, TabbedFragment::class.java.simpleName)
                .commit()
        }

        supportActionBar?.elevation = 0f

    }

    private fun subscribe() {
        val detailObserver = Observer<DetailUser> { aDetail ->
            setView(aDetail)
        }
        mLiveDetailUser.getDetail().observe(this, detailObserver)

        val statusObserver = Observer<Boolean> { aStatus ->
            showLoading(aStatus)
        }
        mLiveDetailUser.getStatus().observe(this, statusObserver)
    }

    private fun setView(detailUser: DetailUser) {
        val avatarObject: ImageView = findViewById(R.id.imageView)
        val nameObject: TextView = findViewById(R.id.name_object)
        val usernameObject: TextView = findViewById(R.id.username_object)
        val companyObject: TextView = findViewById(R.id.company_object)
        val locationObject: TextView = findViewById(R.id.location_object)
        val repositoryObject: TextView = findViewById(R.id.repository_object)

        Glide.with(avatarObject.context)
            .load(detailUser.avatar)
            .circleCrop()
            .into(avatarObject)

        val username = "${detailUser.username}"
        usernameObject.text = username

        val name = "${detailUser.name}"
        nameObject.text = name

        val company = "Company : ${detailUser.company}"
        companyObject.text = company

        val location = "Location : ${detailUser.location}"
        locationObject.text = location

        val repository = "Repository : ${detailUser.repository}"
        repositoryObject.text = repository
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
            binding.constraint2.visibility = View.GONE
            binding.frameContainer.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.constraint2.visibility = View.VISIBLE
            binding.frameContainer.visibility = View.VISIBLE
        }
    }

    companion object {
        private const val TAG = "DetailUserActivity"
        const val EXTRA_USER = "extra_user"
    }

}