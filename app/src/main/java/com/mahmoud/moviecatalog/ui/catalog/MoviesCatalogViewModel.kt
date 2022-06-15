package com.mahmoud.moviecatalog.ui.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.mahmoud.common.entities.Movie
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

    val moviesList: LiveData<Result<PagingData<Movie>>> = MutableLiveData()

    override fun setup() {
        observeMovies()
    }

    private fun observeMovies() {
        launchCoroutine {
            catalogUseCase.getPopularMovies()
                .catch { error ->
                    moviesList.postValue(Result.Error(error))
                }
                .collect { movies ->
                    moviesList.postValue(Result.Success(movies))
                }
        }
    }

}
