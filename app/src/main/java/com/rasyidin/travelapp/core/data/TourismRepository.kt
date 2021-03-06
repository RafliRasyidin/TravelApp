package com.rasyidin.travelapp.core.data

import android.util.Log
import com.rasyidin.travelapp.core.data.source.remote.RemoteDataSource
import com.rasyidin.travelapp.core.data.source.remote.network.ApiResponse
import com.rasyidin.travelapp.core.domain.model.Tourism
import com.rasyidin.travelapp.core.domain.repository.ITourismRepository
import com.rasyidin.travelapp.core.utils.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class TourismRepository(private val remoteDataSource: RemoteDataSource) : ITourismRepository {
    override fun getAllTourism(): Flow<Resource<List<Tourism>>> {
        return flow {
            remoteDataSource.getAllTourism().collect { response ->
                when (response) {
                    is ApiResponse.Success -> {
                        emit(Resource.Success(Mapper.tourismResponseToTourism(response.data)))
                        Log.d("TourismRepository", "data : ${response.data}")
                    }
                    is ApiResponse.Error -> {
                        emit(Resource.Error<List<Tourism>>(null, response.message))
                        Log.e("TourismRepository", response.message)
                    }
                    is ApiResponse.Empty -> Log.e("TourismRepository", response.toString())
                }
            }
        }
    }
}
