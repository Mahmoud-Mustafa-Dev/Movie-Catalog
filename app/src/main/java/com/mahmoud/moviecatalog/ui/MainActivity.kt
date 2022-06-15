package com.mahmoud.moviecatalog.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.mahmoud.moviecatalog.R
import com.mahmoud.moviecatalog.databinding.ActivityMainBinding
import com.mahmoud.moviecatalog.ui.catalog.MoviesCatalogFragment
import com.mahmoud.moviecatalog.ui.movie_details.MovieDetailsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onBackPressed() {
        when (getForegroundFragment()) {
            is MoviesCatalogFragment -> super.onBackPressed()
            is MovieDetailsFragment -> binding.navHostFragment.findNavController().popBackStack()
            else -> binding.navHostFragment.findNavController().popBackStack()
        }
    }

    private fun getForegroundFragment(): Fragment? {
        val navHostFragment: Fragment? =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        return navHostFragment?.childFragmentManager?.fragments?.get(0)
    }
}
