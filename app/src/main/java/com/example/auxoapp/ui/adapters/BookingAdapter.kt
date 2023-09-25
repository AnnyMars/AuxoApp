package com.example.auxoapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.auxoapp.data.models.BookingInfo
import com.example.auxoapp.databinding.BookingCardLayoutBinding

class BookingAdapter(
    private val list: MutableList<BookingInfo>
): RecyclerView.Adapter<BookingAdapter.MyViewHolder>() {
    class MyViewHolder(private val binding: BookingCardLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(bookingData: BookingInfo){
            with(binding){
                bookingDate.text = bookingData.date
                bookingTime.text = bookingData.time
                bookingName.text = bookingData.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingAdapter.MyViewHolder {
        return BookingAdapter.MyViewHolder(
            BookingCardLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = list[position]
        holder.bind(data)
    }
}