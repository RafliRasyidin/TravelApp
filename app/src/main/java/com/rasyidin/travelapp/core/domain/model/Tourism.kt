package com.rasyidin.travelapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tourism(
        val address: String? = "",
        val description: String? = "",
        val id: Int? = 0,
        val image: String? = "",
        val like: Int? = 0,
        val name: String? = ""
) : Parcelable