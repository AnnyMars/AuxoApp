package com.example.auxoapp.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.auxoapp.R
import com.example.auxoapp.data.models.BookingInfo
import com.example.auxoapp.databinding.FragmentDetailBinding
import com.example.auxoapp.ui.adapters.BookingAdapter
import com.example.auxoapp.ui.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookingList.forEach {
            viewModel.insertBooking(it)
        }

        viewModel.getRoomById(args.id)
        viewModel.room.observe(viewLifecycleOwner){
            with(binding){
                roomName.text = it.name
                roomPlace.text = it.place
                roomTime.text = it.time
                if (it.icon1) roomDescIconText.text = getString(R.string.detailDeskText)
                if (it.icon2) roomProjectorIconText.text = getString(R.string.detailProjectorText)
            }
        }
        setupRecyclerView()

        if (binding.detailButton.isEnabled){
            binding.detailButton.setOnClickListener {
                Toast.makeText(requireContext(), getString(R.string.detailToatText), Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView(){

        val recyclerView = binding.bookingRecyclerView

        viewModel.getBookingByRoomId(args.id).observe(viewLifecycleOwner){
            recyclerView.adapter = BookingAdapter(it.toMutableList())

            if (it.isNotEmpty()){
                binding.detailButton.isEnabled = false
            }
        }


    }

    private val bookingList: List<BookingInfo> = listOf(
        BookingInfo(
            id = 1,
            roomId = 1,
            date = "25.01.2023",
            time = "12:00-13:00",
            name = "Андрей Корпанюк"
        ),
        BookingInfo(
            id = 2,
            roomId = 1,
            date = "25.01.2023",
            time = "13:00-14:00",
            name = "Андрей Корпанюк"
        ),
        BookingInfo(
            id = 3,
            roomId = 4,
            date = "25.01.2023",
            time = "13:00-14:00",
            name = "Иванов Андрей"
        ),
        BookingInfo(
            id = 4,
            roomId = 1,
            date = "25.01.2023",
            time = "14:00-15:00",
            name = "Кулаженко Анатолий"
        ),
        BookingInfo(
            id = 5,
            roomId = 2,
            date = "25.01.2023",
            time = "14:00-15:00",
            name = "Смирнов Степан"
        ),
        BookingInfo(
            id = 6,
            roomId = 2,
            date = "25.01.2023",
            time = "14:00-15:00",
            name = "Иванов Иван"
        )
    )
}

