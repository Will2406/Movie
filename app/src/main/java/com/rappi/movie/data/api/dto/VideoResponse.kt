package com.rappi.movie.data.api.dto

import com.squareup.moshi.Json

data class VideoResponse(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "results") val results: List<VideoItemResponse>,
) {
    data class VideoItemResponse(
        @field:Json(name = "key") val key: String,
        @field:Json(name = "iso_639_1") val iso639: String,
        @field:Json(name = "iso_3166_1") val iso3166: String,
        @field:Json(name = "name") val name: String,
        @field:Json(name = "site") val site: String,
        @field:Json(name = "size") val size: Int,
        @field:Json(name = "official") val official: Boolean,
        @field:Json(name = "type") val type: String,
        @field:Json(name = "published_at") val publishedAt: String,
        @field:Json(name = "id") val id: String,
    )
}
