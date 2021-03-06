package com.rasyidin.travelapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TourismResponse(

        @field:SerializedName("address")
        val address: String? = "",

        @field:SerializedName("description")
        val description: String? = "",

        @field:SerializedName("id")
        val id: Int? = 0,

        @field:SerializedName("image")
        val image: String? = "",

        @field:SerializedName("like")
        val like: Int? = 0,

        @field:SerializedName("name")
        val name: String? = ""
)