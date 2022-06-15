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
import com.mahmoud.common.entities.Movie
import com.mahmoud.common.entities.Result
import com.mahmoud.common.entities.handleWith
import com.mahmoud.common.extensions.*
import com.mahmoud.common.listeners.MovieCardListener
import com.mahmoud.moviecatalog.databinding.MoviesCatalogFragmentBinding
import com.mahmoud.moviecatalog.ui.base.BaseFragment
import com.mahmoud.moviecatalog.ui.catalog.movies.MoviesAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesCatalogFragment : BaseFragment<MoviesCatalogFragmentBinding, MoviesCatalogViewModel>(),
    MovieCardListener {

    private lateinit var movieCardListener: MovieCardListener

    private val popularMoviesAdapter = MoviesAdapter(this)

    override val viewModel by viewModel<MoviesCatalogViewModel>()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): MoviesCatalogFragmentBinding {
        return MoviesCatalogFragmentBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPopularMoviesRV()
        observe(viewModel.popularMoviesList, ::observePopularMovies)
        observeAdapterState()
    }

    private fun setupPopularMoviesRV() {
        binding.rvPopular.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = popularMoviesAdapter
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

    private fun observeAdapterState() {
        lifecycleScope.launch {
            lifecycle.whenStarted {
                popularMoviesAdapter.loadStateFlow.collectLatest { loadStates ->
                    when {
                        loadStates.isInitializing()
                                || loadStates.isLoadingRefresh() -> {
                            binding.popularMoviesShimmerViewContainer.show()
                            binding.rvPopular.hide()
                        }
                        loadStates.isDataLoaded()
                                || loadStates.isNoMoreData(popularMoviesAdapter.itemCount) -> {
                            binding.popularMoviesShimmerViewContainer.hide()
                            binding.rvPopular.show()

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

    override fun onMovieClicked() {
        TODO("Not yet implemented")
    }
}
