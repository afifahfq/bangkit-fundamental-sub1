package com.example.githubuser.Database

<<<<<<< Updated upstream
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "username")
    var title: String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null,

    @ColumnInfo(name = "avatar")
    var date: String? = null
) : Parcelable
=======
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
>>>>>>> Stashed changes
