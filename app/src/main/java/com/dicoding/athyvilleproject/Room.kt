package com.dicoding.athyvilleproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Room(
    val room_name: String,
    val room_price: Int,
    val room_img: String,
    val room_facility: String,
    val room_personil: Int,
    val room_area: Int,
    val room_desc: String,
    val room_rate: Int
) : Parcelable
