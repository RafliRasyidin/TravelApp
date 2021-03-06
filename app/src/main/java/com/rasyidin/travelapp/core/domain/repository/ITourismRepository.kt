package com.rasyidin.travelapp.core.domain.repository

import com.rasyidin.travelapp.core.data.Resource
import com.rasyidin.travelapp.core.domain.model.Tourism
import kotlinx.coroutines.flow.Flow

interface ITourismRepository {

    fun getAllTourism(): Flow<Resource<List<Tourism>>>
}