package com.example.githubuser.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data class Favorite(
    // warning primary key
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "id")
//    val id: Int = 0,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "username")
    val username: String?,

    @ColumnInfo(name = "deskripsi")
    val deskripsi: String?,

    @ColumnInfo(name = "avatar")
    val avatar: String?
)
