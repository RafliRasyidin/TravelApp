package com.rasyidin.travelapp.core.domain.usecase

import com.rasyidin.travelapp.core.data.Resource
import com.rasyidin.travelapp.core.domain.model.Tourism
import com.rasyidin.travelapp.core.domain.repository.ITourismRepository
import kotlinx.coroutines.flow.Flow

class TourismInteractor(private val tourismRepository: ITourismRepository) : TourismUseCase {

    override fun getAllTourism(): Flow<Resource<List<Tourism>>> {
        return tourismRepository.getAllTourism()
    }
}