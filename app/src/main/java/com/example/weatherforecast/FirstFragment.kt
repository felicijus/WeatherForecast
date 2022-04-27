package com.example.weatherforecast

import WeatherViewModelFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.weatherforecast.databinding.FragmentFirstBinding
import com.example.weatherforecast.view.CountViewModel
import com.example.weatherforecast.view.WeatherListAdapter
import com.example.weatherforecast.view.WeatherViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val weatherViewModel:WeatherViewModel by activityViewModels {
        WeatherViewModelFactory((activity?.application as WeathersApplication).repository)
    }

    private val countViewModel: CountViewModel by activityViewModels()

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

        //recyclerView.addItemDecoration(DividerItemDecoration())

        weatherViewModel.weathers.observe(viewLifecycleOwner, Observer { items ->
            items.let { adapter.submitList(it) }
        })



        //CountViewModel
        binding.buttonMaterial.text = countViewModel.count.toString()

        binding.buttonMaterial.setOnClickListener {
            countViewModel.update()
            binding.buttonMaterial.text = countViewModel.count.toString()
        }



        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}