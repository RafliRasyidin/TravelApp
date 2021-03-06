package com.rasyidin.travelapp.core.utils

import com.rasyidin.travelapp.core.data.source.remote.response.TourismResponse
import com.rasyidin.travelapp.core.domain.model.Tourism

object Mapper {

    fun tourismResponseToTourism(input: List<TourismResponse>): List<Tourism> {
        val tourismList = ArrayList<Tourism>()
        input.map {
            val tourism = Tourism(
                address = it.address,
                description = it.description,
                id = it.id,
                image = it.image,
                like = it.like,
                name = it.name
            )
            tourismList.add(tourism)
        }
        return tourismList
    }
}