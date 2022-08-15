package com.rappi.movie.domain.model

import android.os.Parcelable
import com.rappi.movie.BuildConfig
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieModel(
    val adult: Boolean,
    val backdropPath: String,
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: String,
    val originalLanguage: String
) : Parcelable {
    fun getPosterPathUrl() = "${BuildConfig.BASE_IMAGE_URL}w500/$posterPath"
    fun getBackdropPathUrl() = "${BuildConfig.BASE_IMAGE_URL}original/$backdropPath"
    fun getYearReleaseDate() = releaseDate.substring(0, 4)
    fun getVote() = voteAverage.substring(0, 3)

    companion object {
        const val TAG = "MovieModel"
    }
}