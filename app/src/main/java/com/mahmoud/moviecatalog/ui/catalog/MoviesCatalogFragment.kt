package com.mahmoud.moviecatalog.ui.catalog


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mahmoud.common.entities.Movie
import com.mahmoud.common.entities.Result
import com.mahmoud.common.entities.handleWith
import com.mahmoud.common.extensions.observe
import com.mahmoud.common.listeners.MovieCardListener
import com.mahmoud.moviecatalog.databinding.MoviesCatalogFragmentBinding
import com.mahmoud.moviecatalog.ui.base.BaseFragment
import com.mahmoud.moviecatalog.ui.catalog.movies.MoviesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesCatalogFragment : BaseFragment<MoviesCatalogFragmentBinding, MoviesCatalogViewModel>(),
    MovieCardListener {

    private lateinit var movieCardListener: MovieCardListener

    private val moviesAdapter: MoviesAdapter = MoviesAdapter(this)

    override val viewModel by viewModel<MoviesCatalogViewModel>()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): MoviesCatalogFragmentBinding {
        return MoviesCatalogFragmentBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.moviesList, ::observePopularMovies)
    }

    private fun observePopularMovies(result: Result<List<Movie>>) {
        result.handleWith(
            activity = requireActivity(),
            success = { movies ->
                //movies recieved
            },
            error = {
                //there was an error

            }
        )
    }

    private fun setUpMoviesList() {
        //todo pass the list of movies to the adapter
    }

    override fun onMovieClicked() {
        TODO("Not yet implemented")
    }

    override fun onMoviesLoaded() {
        TODO("Not yet implemented")
    }
}
