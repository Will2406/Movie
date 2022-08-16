package com.rappi.movie.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rappi.movie.R
import com.rappi.movie.databinding.FragmentMovieBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieViewModel by activityViewModels()

    private lateinit var upcomingMovieAdapter: MovieAdapter
    private lateinit var trendingMovieAdapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setupAdapter()
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.upcomingMoviesList.observe(viewLifecycleOwner) {
            upcomingMovieAdapter.submitList(it)
        }

        viewModel.trendingMoviesList.observe(viewLifecycleOwner) {
            trendingMovieAdapter.submitList(it)
        }
    }

    private fun setupAdapter() {
        upcomingMovieAdapter = MovieAdapter()
        trendingMovieAdapter = MovieAdapter()

        binding.recyclerUpcomingRelease.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerUpcomingRelease.adapter = upcomingMovieAdapter

        binding.recyclerTrending.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerTrending.adapter = trendingMovieAdapter

        upcomingMovieAdapter.listener =
            MovieListener { movie ->
                val bundle = bundleOf("movie" to movie)
                findNavController().navigate(R.id.action_movieFragment_to_movieDetailFragment, bundle)
            }
        trendingMovieAdapter.listener = MovieListener { movie ->
            val bundle = bundleOf("movie" to movie)
            findNavController().navigate(R.id.action_movieFragment_to_movieDetailFragment, bundle)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        //avoid memory lake
        _binding = null
    }

}