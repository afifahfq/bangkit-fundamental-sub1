package com.example.githubuser.Database

import androidx.lifecycle.LiveData

class FavoriteRepository(private val favoriteDao: FavoriteDao) {
    val readAllData: LiveData<List<Favorite>> = favoriteDao.readAllFavorites()

    suspend fun addFavorite(favorite: Favorite){
        favoriteDao.addFavorite(favorite)
    }

    suspend fun deleteFavorite(username: String){
        favoriteDao.deleteFavorite(username)
    }

    suspend fun deleteAllFavorites(){
        favoriteDao.deleteAllFavorites()
    }

    fun checkFavoriteExist(username: String){
        /*val checkFavoriteExist: LiveData<List<Favorite>> = favoriteDao.checkFavoriteExist(id) */

        favoriteDao.checkFavoriteExist(username)
    }
}