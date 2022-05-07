package com.example.weatherforecast

import WeatherViewModelFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.weatherforecast.database.Weather
import com.example.weatherforecast.databinding.FragmentFirstBinding
import com.example.weatherforecast.view.WeatherListAdapter
import com.example.weatherforecast.view.WeatherViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(){

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val weatherViewModel:WeatherViewModel by activityViewModels {
        WeatherViewModelFactory((activity?.application as WeathersApplication).repository)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //RecyclerView
        val recyclerView = binding.recyclerView
        val adapter = WeatherListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(),2)


        adapter.setOnItemLongClickListener(object:WeatherListAdapter.OnItemLongClickListener{
            override fun setOnItemLongClickListener(position: Int) {
                Toast.makeText(requireContext(), "Delete Weather with Id "+ adapter.currentList[position].id.toString() , Toast.LENGTH_SHORT).show()
                weatherViewModel.delete(adapter.currentList[position] as Weather)

                adapter.notifyItemRemoved(position)
            }
        })

        adapter.setOnItemClickListener(object:WeatherListAdapter.OnItemClickListener{
            override fun setOnItemClickListener(position: Int) {
                Toast.makeText(requireContext(), "Update Weather with Id "+ adapter.currentList[position].id.toString() , Toast.LENGTH_SHORT).show()

                adapter.notifyItemChanged(position)
            }

        })



        weatherViewModel.weathers.observe(viewLifecycleOwner, Observer { items ->
            items.let { adapter.submitList(it) }
        })

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}