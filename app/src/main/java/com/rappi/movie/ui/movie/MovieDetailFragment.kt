package com.rappi.movie.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.rappi.movie.databinding.FragmentMovieDetailBinding
import com.rappi.movie.domain.model.MovieModel
import com.rappi.movie.ui.video_player.PlayMovieActivity

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private val viewModel: MovieDetailViewModel by viewModels()

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setupViewModel()
        setupView()

    }

    private fun setupView() {
        val movie: MovieModel? = arguments?.getParcelable("movie")
        movie?.let { binding.movie = it }

        binding.btnTrailer.setOnClickListener { viewModel.getVideo(movie?.id ?: 1) }
    }

    private fun setupViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getVideos.flowWithLifecycle(lifecycle).collectLatest {
                if (it.isNotEmpty()) {
                    val intent = Intent(requireContext(), PlayMovieActivity::class.java)
                    intent.putExtra("key", it[0].key)
                    startActivity(intent)
                } else {
                    Toast.makeText(requireContext(), "No se encontro Trailer", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //avoid memory lake
        _binding = null
    }

}