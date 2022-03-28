package com.example.githubuser.ViewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.githubuser.Database.Favorite
import com.example.githubuser.Repository.FavoriteRepository

class FavoriteAddUpdateViewModel(application: Application) : ViewModel()  {
    private val mNoteRepository: FavoriteRepository = FavoriteRepository(application)
    fun insert(user: Favorite) {
        mNoteRepository.insert(user)
    }
    fun update(user: Favorite) {
        mNoteRepository.update(user)
    }
    fun delete(user: Favorite) {
        mNoteRepository.delete(user)
    }
}