package com.example.githubuser

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class User2(
    val username: String?,
    val name: String?,
    val location: String?,
    val repository: Int?,
    val company: String?,
    val followers: Int?,
    val following: Int?,
    val avatar: Int?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
        parcel.writeString(name)
        parcel.writeString(location)
        parcel.writeValue(repository)
        parcel.writeString(company)
        parcel.writeValue(followers)
        parcel.writeValue(following)
        parcel.writeValue(avatar)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User2> {
        override fun createFromParcel(parcel: Parcel): User2 {
            return User2(parcel)
        }

        override fun newArray(size: Int): Array<User2?> {
            return arrayOfNulls(size)
        }
    }
}
