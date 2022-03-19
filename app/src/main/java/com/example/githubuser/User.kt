package com.example.githubuser

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var name: String,
    var description: String,
    var photo: Int
) : Parcelable