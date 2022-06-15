package com.mahmoud.moviecatalog.ui.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mahmoud.common.entities.Movie
import com.mahmoud.common.entities.MoviesType
import com.mahmoud.common.entities.Result
import com.mahmoud.moviecatalog.ui.base.BaseViewModel
import com.mahmoud.use_case.catalog.CatalogUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

class MoviesCatalogViewModel(
    private val catalogUseCase: CatalogUseCase,
    coroutineDispatcher: CoroutineDispatcher
) : BaseViewModel(coroutineDispatcher) {

    val popularMoviesList: LiveData<Result<PagingData<Movie>>> = MutableLiveData()
    val topRatedMoviesList: LiveData<Result<PagingData<Movie>>> = MutableLiveData()
    val revenueMoviesList: LiveData<Result<PagingData<Movie>>> = MutableLiveData()

    override fun setup() {
        observeMovies(MoviesType.PopularMoviesType)
        observeMovies(MoviesType.TopRatedMoviesType)
        observeMovies(MoviesType.RevenueMoviesType)
    }

    private fun observeMovies(moviesType: MoviesType) {
        launchCoroutine {
            catalogUseCase.getMovies(moviesType)
                .cachedIn(viewModelScope)
                .catch { error ->
                    when(moviesType) {
                        is MoviesType.PopularMoviesType -> popularMoviesList.postValue(Result.Error(error))
                        is MoviesType.TopRatedMoviesType -> topRatedMoviesList.postValue(Result.Error(error))
                        is MoviesType.RevenueMoviesType -> revenueMoviesList.postValue(Result.Error(error))
                    }
                }
                .collect { movies ->
                    when(moviesType) {
                        is MoviesType.PopularMoviesType -> popularMoviesList.postValue(Result.Success(movies))
                        is MoviesType.TopRatedMoviesType ->  topRatedMoviesList.postValue(Result.Success(movies))
                        is MoviesType.RevenueMoviesType -> revenueMoviesList.postValue(Result.Success(movies))
                    }
                }
        }
    }

}
