package com.mahmoud.moviecatalog

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.module

object ApplicationModuleProvider{
    fun getModules(): List<Module> {
        //todo return all the desired modules
        return listOf(viewModelModule)
    }
}

private const val IO_DISPATCHER_QUALIFIER = "io"

private fun Scope.ioDispatcher(): CoroutineDispatcher {
    return get(named(IO_DISPATCHER_QUALIFIER))
}

private val viewModelModule = module {

    single (named(IO_DISPATCHER_QUALIFIER)) { Dispatchers.IO}
    //todo add view models here
}
