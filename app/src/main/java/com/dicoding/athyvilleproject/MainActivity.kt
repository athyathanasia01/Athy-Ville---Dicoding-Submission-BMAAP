package com.dicoding.athyvilleproject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.athyvilleproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var listRoomAdapter: ListRoomAdapter

    private val listRoom = ArrayList<Room>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showRecyclerList()

        binding.switchMode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.rvRoom.layoutManager = GridLayoutManager(this, 2)
            } else {
                binding.rvRoom.layoutManager = LinearLayoutManager(this)
            }
        }
    }

    private fun getListRoom() : ArrayList<Room> {
        val room_name = resources.getStringArray(R.array.nama_kamar)
        val room_price = resources.obtainTypedArray(R.array.harga_kamar)
        val room_image = resources.getStringArray(R.array.img_kamar)
        val room_facility = resources.getStringArray(R.array.fasilitas_kamar)
        val room_personil = resources.obtainTypedArray(R.array.orang_per_kamar)
        val room_area = resources.obtainTypedArray(R.array.luas_kamar)
        val room_desc = resources.getStringArray(R.array.deskripsi_kamar)
        val room_rate = resources.obtainTypedArray(R.array.rating_kamar)
        for (i in room_name.indices) {
            val room = Room(
                room_name[i],
                room_price.getInt(i, -1),
                room_image[i],
                room_facility[i],
                room_personil.getInt(i, -1),
                room_area.getInt(i, -1),
                room_desc[i],
                room_rate.getInt(i, -1)
            )

            listRoom.add(room)
        }
        Log.d("room indices", "getListRoom: ${listRoom.size}")
        return listRoom
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showRecyclerList() {
        binding.rvRoom.setHasFixedSize(true)
        binding.rvRoom.layoutManager = LinearLayoutManager(this)

        listRoomAdapter = ListRoomAdapter(listRoom)
        binding.rvRoom.adapter = listRoomAdapter

        listRoom.clear()
        listRoom.addAll(getListRoom())
        listRoomAdapter.notifyDataSetChanged()

        listRoomAdapter.setOnItemClickCallback(object : ListRoomAdapter.OnItemClickCallback {
            override fun OnItemClicked(room: Room) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_ROOM_DETAIL, room)
                Log.e(
                    "toDetailActivity",
                    "OnItemClicked: " +
                            "${room.room_area}, " +
                            "${room.room_rate}, " +
                            "${room.room_desc}, " +
                            "${room.room_price}, " +
                            "${room.room_personil}, " +
                            "${room.room_facility}, " +
                            "${room.room_name}, " +
                            room.room_img,
                    )
                startActivity(intent)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.profil -> {
                val intent = Intent(this@MainActivity, ProfilActivity::class.java)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

}