package com.example.auxoapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.auxoapp.data.models.RoomInfo
import com.example.auxoapp.databinding.CardLayoutBinding
import android.view.View

class MainListAdapter(
    private val list: MutableList<RoomInfo>,
    private val listener: OnItemClickListener
): RecyclerView.Adapter<MainListAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: CardLayoutBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(roomData: RoomInfo){
            with(binding){
                roomName.text = roomData.name
                roomPlace.text= roomData.place
                roomTime.text = roomData.time
                if (roomData.icon2) roomDeskIcon.visibility = View.VISIBLE
                if (roomData.icon1) roomProjectorIcon.visibility = View.VISIBLE
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CardLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = list[position]
        holder.bind(data)
        holder.itemView.setOnClickListener { listener.onItemClick(data.id) }
    }
}