package com.rappi.movie.ui.video_player

import android.os.Bundle
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.rappi.movie.BuildConfig
import com.rappi.movie.databinding.ActivityPlayMovieBinding


class PlayMovieActivity : YouTubeBaseActivity() {

    private var _binding: ActivityPlayMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPlayMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupVideo()
    }

    private fun setupVideo() {
        binding.youtubePlayerFragment.initialize(
            BuildConfig.YOUTUBE_API_KEY,
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider?,
                    player: YouTubePlayer?,
                    bln: Boolean
                ) {
                    player?.loadVideo(intent.getStringExtra("key"))
                    player?.play()
                }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider?,
                    result: YouTubeInitializationResult?
                ) {
                    Toast.makeText(applicationContext, "No se encontro Trailer", Toast.LENGTH_SHORT).show()
                }
            })
    }


}