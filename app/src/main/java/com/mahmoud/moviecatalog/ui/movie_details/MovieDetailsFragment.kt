package com.mahmoud.moviecatalog.ui.movie_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.mahmoud.common.entities.Movie
import com.mahmoud.moviecatalog.R
import com.mahmoud.moviecatalog.databinding.FragmentMovieDetailsBinding
import com.mahmoud.moviecatalog.extensions.loadImage
import com.mahmoud.network.catalog.retrofit.RetrofitConstants

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding: FragmentMovieDetailsBinding get() = _binding!!

    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieDetailsBinding.bind(view)


        binding.bar.toolbar.title = getString(R.string.details)
        populateMovieInformation(args.movie)
    }

    private fun populateMovieInformation(movie: Movie) {
        val rating = getString(R.string.rating) + " " + movie.vote_average.toString()
        binding.ivMovieCover.loadImage(RetrofitConstants.IMAGE_BASE_URL + movie.poster_path)
        binding.tvMovieTitle.text = movie.original_title
        binding.tvMovieOverview.text = movie.overview
        binding.tvMovieRating.text = rating
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
