package com.example.githubuser.ViewModels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.Database.Favorite
import com.example.githubuser.Repository.FavoriteRepository

class MainVewModel(application: Application) : ViewModel()  {
    private val mNoteRepository: FavoriteRepository = FavoriteRepository(application)

    fun getAllNotes(): LiveData<List<Favorite>> = mNoteRepository.getAllNotes()
}