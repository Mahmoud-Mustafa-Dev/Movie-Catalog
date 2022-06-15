package com.mahmoud.common.listeners

import com.mahmoud.common.entities.Movie

interface MovieCardListener {
    fun onMovieClicked(movie: Movie)
}
