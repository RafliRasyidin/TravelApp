package com.rasyidin.travelapp.core.domain.usecase

import com.rasyidin.travelapp.core.data.Resource
import com.rasyidin.travelapp.core.domain.model.Tourism
import kotlinx.coroutines.flow.Flow

interface TourismUseCase {

    fun getAllTourism(): Flow<Resource<List<Tourism>>>
}