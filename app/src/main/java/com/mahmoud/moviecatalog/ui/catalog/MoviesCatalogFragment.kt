package com.mahmoud.moviecatalog.ui.catalog


import android.view.LayoutInflater
import android.view.ViewGroup
import com.mahmoud.moviecatalog.databinding.MoviesCatalogFragmentBinding
import com.mahmoud.moviecatalog.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesCatalogFragment : BaseFragment<MoviesCatalogFragmentBinding, MoviesCatalogViewModel>() {



    override val viewModel by viewModel<MoviesCatalogViewModel>()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): MoviesCatalogFragmentBinding {
        return MoviesCatalogFragmentBinding.inflate(inflater, container, false)
    }
}
