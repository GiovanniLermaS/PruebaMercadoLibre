package com.example.pruebamercadolibre.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pruebamercadolibre.di.util.ViewModelKey
import com.example.pruebamercadolibre.util.ViewModelFactory
import com.example.pruebamercadolibre.viewmodel.DetailActivityViewModel
import com.example.pruebamercadolibre.viewmodel.MainActivityViewModel
import com.example.pruebamercadolibre.viewmodel.ResultActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(ResultActivityViewModel::class)
    abstract fun bindResultActivityViewModel(resultActivityViewModel: ResultActivityViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(DetailActivityViewModel::class)
    abstract fun bindDetailActivityViewModel(detailActivityViewModel: DetailActivityViewModel?): ViewModel?

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory?): ViewModelProvider.Factory?
}