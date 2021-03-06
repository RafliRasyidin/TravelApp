package com.rasyidin.travelapp.core.data.source.remote

import android.util.Log
import com.rasyidin.travelapp.core.data.source.remote.network.ApiResponse
import com.rasyidin.travelapp.core.data.source.remote.network.ApiService
import com.rasyidin.travelapp.core.data.source.remote.response.TourismResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllTourism(): Flow<ApiResponse<List<TourismResponse>>> {
        return flow {
            try {
                val response = apiService.getList()
                val data = response.places
                if (data?.isNotEmpty() ?: return@flow) {
                    emit(ApiResponse.Success(data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}