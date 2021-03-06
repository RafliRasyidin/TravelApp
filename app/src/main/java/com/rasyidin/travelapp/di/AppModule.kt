package com.rasyidin.travelapp.di

import com.rasyidin.travelapp.core.domain.usecase.TourismInteractor
import com.rasyidin.travelapp.core.domain.usecase.TourismUseCase
import com.rasyidin.travelapp.ui.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<TourismUseCase> { TourismInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}