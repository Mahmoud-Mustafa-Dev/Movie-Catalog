package com.mahmoud.repository

import com.mahmoud.network.catalog.NetworkModuleProvider
import com.mahmoud.repository.catalog.CatalogRepository
import org.koin.core.module.Module
import org.koin.dsl.module

object RepositoryModuleProvider{
    fun getModules(): List<Module> {
        val networkModule = NetworkModuleProvider.getModules()
        return listOf(repositoryModule).plus(networkModule)
    }
}

private val repositoryModule = module {
    single { CatalogRepository( get() ) }

}
