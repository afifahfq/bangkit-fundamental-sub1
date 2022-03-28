package com.example.githubuser.Repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.githubuser.Database.Favorite
import com.example.githubuser.Database.FavoriteDao
import com.example.githubuser.Database.FavoriteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(application: Application) {
    private val mNotesDao: FavoriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    init {
        val db = FavoriteRoomDatabase.getDatabase(application)
        mNotesDao = db.favoriteDao()
    }
    fun getAllNotes(): LiveData<List<Favorite>> = mNotesDao.getAllNotes()
    fun insert(user: Favorite) {
        executorService.execute { mNotesDao.insert(user) }
    }
    fun delete(user: Favorite) {
        executorService.execute { mNotesDao.delete(user) }
    }
    fun update(user: Favorite) {
        executorService.execute { mNotesDao.update(user) }
    }
}