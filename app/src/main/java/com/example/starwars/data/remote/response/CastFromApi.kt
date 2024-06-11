package com.example.starwars.data.remote.response

import com.google.gson.annotations.SerializedName

data class CastFromApi(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("profile_path") val profile_path: String?,
    @SerializedName("original_name") val original_name : String,
)