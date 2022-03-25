package com.example.githubuser

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val username: String?,
    val url: String?,
    val avatar: String?
): Parcelable
