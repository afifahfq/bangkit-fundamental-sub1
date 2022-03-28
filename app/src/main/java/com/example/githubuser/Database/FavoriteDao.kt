package com.example.githubuser.Database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: Favorite)

    @Update
    fun update(user: Favorite)

    @Delete
    fun delete(user: Favorite)

    @Query("SELECT * from user ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Favorite>>
}