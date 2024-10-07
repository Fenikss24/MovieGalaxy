package com.example.moviegalaxy.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetails(
    val id: Int,
    val title: String,
    val poster_path: String,
    val casts: List<Cast>,
    val release_date: String,
    val overview: String,
    val runtime: Int?,
    val original_title: String,
    val vote_average: Double,
) : Parcelable
