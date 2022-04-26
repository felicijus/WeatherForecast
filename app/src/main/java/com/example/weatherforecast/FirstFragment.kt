package com.example.weatherforecast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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

    private val weatherViewModel:WeatherViewModel by activityViewModels()
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


        val recyclerView = binding.recyclerView
        val adapter = WeatherListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

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