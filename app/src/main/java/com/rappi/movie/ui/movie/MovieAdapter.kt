package com.rappi.movie.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rappi.movie.databinding.ItemMovieBinding
import com.rappi.movie.domain.model.MovieModel

class MovieAdapter : ListAdapter<MovieModel, MovieAdapter.ViewHolder>(MovieDiffCallback()) {

    var listener: MovieListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let {
            holder.bind(it, listener)
        }
    }

    class ViewHolder(private val view: ItemMovieBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(movie: MovieModel, listener: MovieListener?) = with(view) {
            this.movie = movie
            imgMovie.setOnClickListener { listener?.goToDetail(movie) }
        }
    }


    class MovieDiffCallback : DiffUtil.ItemCallback<MovieModel>() {
        override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem == newItem
        }
    }


}

fun interface MovieListener {
    fun goToDetail(movie: MovieModel)
}