package com.mahmoud.moviecatalog.ui.catalog


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mahmoud.common.entities.Movie
import com.mahmoud.common.entities.MovieFilters
import com.mahmoud.common.entities.Result
import com.mahmoud.common.entities.handleWith
import com.mahmoud.moviecatalog.extensions.*
import com.mahmoud.common.listeners.MovieCardListener
import com.mahmoud.moviecatalog.R
import com.mahmoud.moviecatalog.databinding.FragmentMoviesCatalogBinding
import com.mahmoud.moviecatalog.ui.base.BaseFragment
import com.mahmoud.moviecatalog.ui.catalog.movies.MoviesAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesCatalogFragment : BaseFragment<FragmentMoviesCatalogBinding, MoviesCatalogViewModel>(),
    MovieCardListener {

    private val popularMoviesAdapter = MoviesAdapter(this)
    private val topRatedMoviesAdapter = MoviesAdapter(this)
    private val revenueMoviesAdapter = MoviesAdapter(this)

    override val viewModel by viewModel<MoviesCatalogViewModel>()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMoviesCatalogBinding {
        return FragmentMoviesCatalogBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bar.toolbar.title = getString(R.string.catalog)
        setUpRecyclerViews()

        observe(viewModel.popularMoviesList, ::observePopularMovies)
        observe(viewModel.topRatedMoviesList, ::observeTopRatedMovies)
        observe(viewModel.revenueMoviesList, ::observeRevenueMovies)

        observeAdapterState(popularMoviesAdapter, MovieFilters.POPULAR)
        observeAdapterState(topRatedMoviesAdapter, MovieFilters.TOP_RATED)
        observeAdapterState(revenueMoviesAdapter, MovieFilters.REVENUE)
    }

    private fun setUpRecyclerViews() {
        setupRecyclerViews(binding.rvPopular, MovieFilters.POPULAR)
        setupRecyclerViews(binding.rvTopRated, MovieFilters.TOP_RATED)
        setupRecyclerViews(binding.rvRevenue, MovieFilters.REVENUE)
    }

    private fun setupRecyclerViews(rv: RecyclerView, filter: MovieFilters) {
        rv.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = when (filter) {
                MovieFilters.POPULAR -> popularMoviesAdapter
                MovieFilters.TOP_RATED -> topRatedMoviesAdapter
                MovieFilters.REVENUE -> revenueMoviesAdapter
            }
            val dividerItemDecoration = DividerItemDecoration(
                this.context,
                (layoutManager as LinearLayoutManager).orientation
            )
            addItemDecoration(dividerItemDecoration)
            doOnPreDraw {
                parentFragment?.startPostponedEnterTransition()
            }
        }
    }


    private fun observePopularMovies(result: Result<PagingData<Movie>>) {
        result.handleWith(
            activity = requireActivity(),
            success = { movies ->
                popularMoviesAdapter.submitData(lifecycle, movies)
            },
            error = {
                // todo handle errors
            }
        )
    }

    private fun observeTopRatedMovies(result: Result<PagingData<Movie>>) {
        result.handleWith(
            activity = requireActivity(),
            success = { movies ->
                topRatedMoviesAdapter.submitData(lifecycle, movies)
            },
            error = {
                // todo handle errors
            }
        )
    }

    private fun observeRevenueMovies(result: Result<PagingData<Movie>>) {
        result.handleWith(
            activity = requireActivity(),
            success = { movies ->
                revenueMoviesAdapter.submitData(lifecycle, movies)
            },
            error = {
                // todo handle errors
            }
        )
    }


    private fun observeAdapterState(moviesAdapter: MoviesAdapter, filter: MovieFilters) {
        lifecycleScope.launch {
            lifecycle.whenStarted {
                moviesAdapter.loadStateFlow.collectLatest { loadStates ->
                    when {
                        loadStates.isInitializing()
                                || loadStates.isLoadingRefresh() -> {
                            when (filter) {
                                MovieFilters.POPULAR -> {
                                    binding.shmrPopular.moviesShimmerViewContainer.show()
                                    binding.rvPopular.hide()
                                }
                                MovieFilters.TOP_RATED -> {
                                    binding.shmrTopRated.moviesShimmerViewContainer.show()
                                    binding.rvTopRated.hide()
                                }
                                MovieFilters.REVENUE -> {
                                    binding.shmrRevenue.moviesShimmerViewContainer.show()
                                    binding.rvRevenue.hide()
                                }
                            }
                        }
                        loadStates.isDataLoaded()
                                || loadStates.isNoMoreData(popularMoviesAdapter.itemCount) -> {
                            when (filter) {
                                MovieFilters.POPULAR -> {
                                    binding.shmrPopular.moviesShimmerViewContainer.hide()
                                    binding.rvPopular.show()
                                }
                                MovieFilters.TOP_RATED -> {
                                    binding.shmrTopRated.moviesShimmerViewContainer.hide()
                                    binding.rvTopRated.show()
                                }
                                MovieFilters.REVENUE -> {
                                    binding.shmrRevenue.moviesShimmerViewContainer.hide()
                                    binding.rvRevenue.show()
                                }
                            }

                        }
                        loadStates.isEmptyState(popularMoviesAdapter.itemCount) -> {
                            //todo handle empty state
                        }
                        loadStates.isError() -> {
                            //todo handle error state
                        }
                    }
                }
            }
        }
    }

    override fun onMovieClicked(movie: Movie) {
        navigate(
            MoviesCatalogFragmentDirections.actionMoviesCatalogFragmentToMovieDetailsFragment(movie)
        )
    }
}
