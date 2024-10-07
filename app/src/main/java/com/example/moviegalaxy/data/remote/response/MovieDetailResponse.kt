package com.example.moviegalaxy.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("original_title")
    @Expose
    val original_title: String,

    @SerializedName("overview")
    @Expose
    val overview: String,

    @Expose
    @SerializedName("poster_path")
    val poster_path: String,

    @Expose
    @SerializedName("release_date")
    val release_date: String,

    @Expose
    @SerializedName("runtime")
    val runtime: Int?,

    @Expose
    @SerializedName("title")
    val title: String,

    @Expose
    @SerializedName("vote_average")
    val vote_average: Double
)
