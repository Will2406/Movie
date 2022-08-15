package com.rappi.movie.domain.mapper

import com.rappi.movie.data.api.dto.VideoResponse
import com.rappi.movie.domain.model.Video

fun VideoResponse.VideoItemResponse.convertToModel() = Video(
    key = key
)

fun List<VideoResponse.VideoItemResponse>.convertToModel() = map(VideoResponse.VideoItemResponse::convertToModel)