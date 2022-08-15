package com.rappi.movie.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rappi.movie.data.safeLaunch
import com.rappi.movie.domain.model.Video
import com.rappi.movie.domain.usecase.GetVideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getVideosUseCase: GetVideosUseCase
) : ViewModel() {

    private val _getVideos = MutableSharedFlow<List<Video>>()
    val getVideos get() = _getVideos.asSharedFlow()


    fun getVideo(id: Int) {
        viewModelScope.safeLaunch {
            getVideosUseCase.invoke(id).collect {
                _getVideos.emit(it)
            }
        }
    }


}