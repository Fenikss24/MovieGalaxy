package com.example.starwars.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VideoResults (
    @SerializedName("iso_639_1") val iso_639_1 : String,
    @SerializedName("iso_3166_1") val iso_3166_1 : String,
    @SerializedName("name") val name : String,
    @SerializedName("key") val key : String,
    @SerializedName("published_at") val published_at : String,
    @SerializedName("site") val site : String,
    @SerializedName("size") val size : Int,
    @SerializedName("type") val type : String,
    @SerializedName("official") val official : Boolean,
    @SerializedName("id") val id : String
): Parcelable