package com.mahmoud.use_case

import com.mahmoud.repository.RepositoryModuleProvider
import com.mahmoud.use_case.catalog.CatalogUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

object UseCaseModuleProvider{
    fun getModules(): List<Module> {
        val repositoryModule = RepositoryModuleProvider.getModules()
        return listOf(useCaseModule).plus(repositoryModule)
    }
}

private val useCaseModule = module {
    single { CatalogUseCase( get() ) }
}
