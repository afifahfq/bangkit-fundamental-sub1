package com.example.githubuser.Models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailUser(
    val username: String?,
    val name: String?,
    val location: String?,
    val repository: Int?,
    val company: String?,
    val followers: String?,
    val following: String?,
    val avatar: String?
): Parcelable