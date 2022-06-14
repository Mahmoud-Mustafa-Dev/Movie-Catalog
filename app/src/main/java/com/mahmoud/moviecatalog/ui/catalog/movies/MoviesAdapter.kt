package com.mahmoud.moviecatalog.ui.catalog.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mahmoud.common.entities.Movie
import com.mahmoud.moviecatalog.R
import com.mahmoud.moviecatalog.databinding.MovieCardBinding
import com.mahmoud.common.listeners.MovieCardListener

class MoviesAdapter(
    private val listener: MovieCardListener,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    MovieCardListener {

    private var data = listOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            MovieCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MoviesViewHolder(binding, this)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder !is MoviesViewHolder) return
        val currentNft = data[position]
        holder.bind(currentNft)
    }

    override fun getItemCount() = data.size


    override fun getItemViewType(position: Int): Int {
        return R.layout.movie_card
    }

    fun setMoviesList(moviesList: List<Movie>) {
        data = moviesList
        notifyDataSetChanged()
    }

    override fun onMovieClicked() {
        TODO("Not yet implemented")
    }

    override fun onMoviesLoaded() {
        TODO("Not yet implemented")
    }
}
