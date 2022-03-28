package com.example.githubuser.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(favorite: Favorite)

    @Query("SELECT * FROM favorite_table ORDER BY id ASC")
    fun readAllFavorites(): LiveData<List<Favorite>>

    /* @Delete
    suspend fun deleteFavorite(favorite: Favorite) */

    @Query("DELETE FROM favorite_table WHERE username = :username")
    suspend fun deleteFavorite(username: String)

    @Query("DELETE FROM favorite_table")
    suspend fun deleteAllFavorites()

    @Query("SELECT * FROM favorite_table WHERE username = :username")
    fun checkFavoriteExist(username : String) : LiveData<List<Favorite>>
}