package com.mahmoud.network.catalog

import com.mahmoud.network.catalog.catalog.CatalogApi
import com.mahmoud.network.catalog.catalog.ICatalogApi
import org.koin.core.module.Module
import org.koin.dsl.module

object NetworkModuleProvider{
    fun getModules(): List<Module> {
        return listOf(networkModule)
    }
}


private val networkModule = module {
    single<ICatalogApi> { CatalogApi() }
}
