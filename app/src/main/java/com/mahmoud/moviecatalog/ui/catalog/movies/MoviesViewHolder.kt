package com.mahmoud.moviecatalog.ui.catalog.movies

import android.annotation.SuppressLint
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.mahmoud.common.entities.Movie
import com.mahmoud.moviecatalog.R
import com.mahmoud.moviecatalog.databinding.MovieCardBinding
import com.mahmoud.common.listeners.MovieCardListener

class MoviesViewHolder(private val binding: MovieCardBinding, private val listener: MovieCardListener) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(movie: Movie) {

        //todo set the movie cover

        binding.ivMovieCover.animation =
            AnimationUtils.loadAnimation(this.itemView.context, R.anim.item_translate_scale)

        binding.ivMovieCover.setOnClickListener {
            listener.onMovieClicked()
        }
    }

    private fun setMovieImage(url: String) {
        //todo set movie cover
    }
}
