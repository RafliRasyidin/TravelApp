package com.rasyidin.travelapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListTourismResponse(
        @field:SerializedName("places")
        val places: List<TourismResponse>?
) 