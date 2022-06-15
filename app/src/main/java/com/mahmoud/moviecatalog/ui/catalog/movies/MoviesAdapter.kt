package com.mahmoud.moviecatalog.ui.catalog.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.mahmoud.common.entities.Movie
import com.mahmoud.moviecatalog.R
import com.mahmoud.moviecatalog.databinding.MovieCardBinding
import com.mahmoud.common.listeners.MovieCardListener

class MoviesAdapter(
    private val listener: MovieCardListener
) :
    PagingDataAdapter<Movie, MoviesViewHolder>(MOVIES_RESULT_COMPARATOR),
    MovieCardListener {

    companion object {
        private val MOVIES_RESULT_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {

            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding =
            MovieCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MoviesViewHolder(binding, this)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        getItem(position)?.let { result ->
            holder.bind(result)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.movie_card
    }

    override fun onMovieClicked() {
        TODO("Not yet implemented")
    }
}
