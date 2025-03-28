package com.dicoding.athyvilleproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.athyvilleproject.databinding.ItemRoomBinding

class ListRoomAdapter(private val listRoom: ArrayList<Room>) : RecyclerView.Adapter<ListRoomAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: ItemRoomBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val binding = ItemRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val ( roomName, roomPrice, roomImage, roomFacility, roomPersonil, roomArea, roomDesc, roomRate ) = listRoom[position]
        val context = holder.itemView.context
        Glide.with(holder.itemView.context)
            .load(roomImage)
            .into(holder.binding.imgRoom)
        holder.binding.apply {
            val format_price = context.getString(R.string.room_price_init, roomPrice.toString())
            val format_rate = context.getString(R.string.rate_init, roomRate.toString())
            val format_pesonil = context.getString(R.string.room_init_personil, roomPersonil.toString())

            tvRoomName.setText(roomName)
            tvRoomPrice.setText(format_price)
            tvRating.setText(format_rate)
            tvFacility.setText(roomFacility)
            tvPersonil.setText(format_pesonil)
        }

        holder.binding.btnCheck.setOnClickListener({
            onItemClickCallback.OnItemClicked(listRoom[holder.adapterPosition])
        })
    }

    override fun getItemCount(): Int {
        return listRoom.size
    }

    interface OnItemClickCallback {
        fun OnItemClicked(data : Room)
    }
}