package com.dicoding.athyvilleproject

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.athyvilleproject.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_ROOM_DETAIL = "extra_room"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val room = if(Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Room>(EXTRA_ROOM_DETAIL, Room::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_ROOM_DETAIL)
        }

        if (room != null) {
            Glide.with(this)
                .load(room.room_img)
                .into(binding.imgRoom)
            binding.apply {
                val format_rate = getString(R.string.rate_init, room.room_rate.toString())
                val format_price = getString(R.string.room_price_init, room.room_price.toString())
                val format_area = getString(R.string.room_init_area, room.room_area.toString())
                val format_personil = getString(R.string.room_init_personil, room.room_personil.toString())

                tvRating.setText(format_rate)
                tvRoomName.setText(room.room_name)
                tvRoomPrice.setText(format_price)
                tvRoomDescription.setText(room.room_desc)
                tvRoomFacilities.setText(room.room_facility)
                tvRoomArea.setText(format_area)
                tvRoomPersonil.setText(format_personil)
            }
        }
    }
}