package com.example.starwars.data.remote.response

import com.google.gson.annotations.SerializedName

data class MovieFromApi(
    @SerializedName("id") val id: Int,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("release_date") val release_date: String,
    @SerializedName("title") val title: String,
    @SerializedName("vote_average") val vote_average: Double,
    @SerializedName("is_in_favorite") val isInFavorite: Boolean
)