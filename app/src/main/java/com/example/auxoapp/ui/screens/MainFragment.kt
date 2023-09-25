package com.example.auxoapp.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.auxoapp.data.models.RoomInfo
import com.example.auxoapp.databinding.FragmentMainBinding
import com.example.auxoapp.ui.adapters.MainListAdapter
import com.example.auxoapp.ui.adapters.OnItemClickListener
import com.example.auxoapp.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        roomList.forEach {
            viewModel.insertRooms(it)
        }

        setupRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView(){
        viewModel.getAllRooms().observe(viewLifecycleOwner){
            binding.recyclerView.adapter = MainListAdapter(it.toMutableList(), object : OnItemClickListener{
                override fun onItemClick(id: Int) {
                    val roomId = it.find { it.id == id }
                    val action = MainFragmentDirections.actionMainFragmentToDetailFragment(roomId!!.id)
                    findNavController().navigate(action)
                }

            })
        }
    }

    private val roomList: List<RoomInfo> = listOf(
        RoomInfo(
            1,
            "Room1",
            "25 мест",
            "11:00-12:00",
            icon1 = true,
            icon2 = true
        ),
        RoomInfo(
            2,
            "Room2",
            "22 мест",
            icon1 = true,
            icon2 = false
        ),
        RoomInfo(
            3,
            "Room3",
            "10 мест",
            icon1 = true,
            icon2 = false
        ),
        RoomInfo(
            4,
            "Room4",
            "5 мест",
            "13:00-14:00",
            icon1 = false,
            icon2 = true
        ),
        RoomInfo(
            5,
            "Room5",
            "10 мест",
            icon1 = true,
            icon2 = true
        )
    )

}

