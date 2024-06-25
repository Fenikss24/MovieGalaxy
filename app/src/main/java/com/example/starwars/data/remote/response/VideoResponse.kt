package com.example.starwars.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VideoResponse (
    @SerializedName("id") val id : Int,
    @SerializedName("results") val results : List<VideoResults>
): Parcelable