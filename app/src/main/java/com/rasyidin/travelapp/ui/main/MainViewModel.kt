package com.rasyidin.travelapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rasyidin.travelapp.core.data.Resource
import com.rasyidin.travelapp.core.domain.model.Tourism
import com.rasyidin.travelapp.core.domain.usecase.TourismUseCase

class MainViewModel(private val tourismUseCase: TourismUseCase) : ViewModel() {

    fun getTourism(): LiveData<Resource<List<Tourism>>> {
        return tourismUseCase.getAllTourism().asLiveData()
    }

}