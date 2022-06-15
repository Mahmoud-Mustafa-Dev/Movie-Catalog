package com.mahmoud.moviecatalog.ui.catalog.movies

import android.annotation.SuppressLint
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.mahmoud.common.entities.Movie
import com.mahmoud.moviecatalog.extensions.loadImage
import com.mahmoud.moviecatalog.databinding.MovieCardBinding
import com.mahmoud.common.listeners.MovieCardListener
import com.mahmoud.moviecatalog.R

import com.mahmoud.network.catalog.retrofit.RetrofitConstants

class MoviesViewHolder(private val binding: MovieCardBinding, private val listener: MovieCardListener) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(movie: Movie) {

        binding.ivMovieCover.loadImage(imageUrl = RetrofitConstants.IMAGE_BASE_URL + movie.poster_path)
        binding.cvMovie.animation =
            AnimationUtils.loadAnimation(this.itemView.context, R.anim.item_translate_scale)


        binding.cvMovie.setOnClickListener {
            listener.onMovieClicked(movie)
        }
    }
}
