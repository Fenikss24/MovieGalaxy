package com.example.moviegalaxy.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cast(
    val id: Int,
    val name: String,
    val profile_path: String?,
    val original_name : String
): Parcelable