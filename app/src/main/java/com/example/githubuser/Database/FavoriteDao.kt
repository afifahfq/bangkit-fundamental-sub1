package com.example.githubuser.Database

import androidx.lifecycle.LiveData
<<<<<<< Updated upstream
import androidx.room.*
=======
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
>>>>>>> Stashed changes

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
<<<<<<< Updated upstream
    fun insert(user: Favorite)

    @Update
    fun update(user: Favorite)

    @Delete
    fun delete(user: Favorite)

    @Query("SELECT * from user ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Favorite>>
=======
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
>>>>>>> Stashed changes
}