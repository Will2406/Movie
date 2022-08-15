package com.rappi.movie.data.api.dto

import com.squareup.moshi.Json

data class UpcomingMovieResponse(
    @field:Json(name = "total_results")  val totalResults: Int?,
    @field:Json(name = "total_pages")  val totalPages: Int?,
    @field:Json(name = "page")  val page: Int?,
    @field:Json(name = "results") val results: List<UpcomingMovie>
)