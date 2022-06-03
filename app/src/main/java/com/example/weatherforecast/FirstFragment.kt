package com.example.weatherforecast

import com.example.weatherforecast.model.WeatherViewModelFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.weatherforecast.database.Weather
import com.example.weatherforecast.databinding.FragmentFirstBinding
import com.example.weatherforecast.view.WeatherListAdapter
import com.example.weatherforecast.model.WeatherViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(){

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    // ViewModel access in a Fragment with application wide context
    private val weatherViewModel: WeatherViewModel by activityViewModels {
        WeatherViewModelFactory((activity?.application as WeathersApplication).repository)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView
        val recyclerView = binding.recyclerView
        val adapter = WeatherListAdapter()
        recyclerView.adapter = adapter      // set the RecyclerViews adapter to your WeatherListAdapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(),1)


        // Override the OnItemClickListener initialized for each item in the WeatherListAdapter
        adapter.setOnItemClickListener(object:WeatherListAdapter.OnItemClickListener{
            override fun setOnItemClickListener(position: Int) {
                Toast.makeText(requireContext(), "Update Weather with Id "+ adapter.currentList[position].id.toString() , Toast.LENGTH_SHORT).show()

                // start a DialogFragment with the Item(a Weather Object) which just got clicked
                val weatherDialogFragment = WeatherUpdateDialogFragment(adapter.currentList[position] as Weather)
                weatherDialogFragment.show(parentFragmentManager,"weatherUpdateDialog")

                // notify the adapter that an Item has been changed -> updates the RecyclerView
                adapter.notifyItemChanged(position)
            }
        })

        // Override the OnItemLongClickListener
        adapter.setOnItemLongClickListener(object:WeatherListAdapter.OnItemLongClickListener{
            override fun setOnItemLongClickListener(position: Int) {
                Toast.makeText(requireContext(), "Delete Weather with Id "+ adapter.currentList[position].id.toString() , Toast.LENGTH_SHORT).show()

                // using the ViewModel to delete the Dataset with the adapters current position
                weatherViewModel.delete(adapter.currentList[position] as Weather)

                // notify the adapter that an Item has been removed -> updates the RecyclerView
                adapter.notifyItemRemoved(position)
            }
        })

        // observe the LiveData<List<Weather>> in the ViewModel,
        // which represents the Flow<List<Weather>> from the Repository
        // which in turn is connected to the Database with the Data access object (DAO) for Data retrieval
        weatherViewModel.weathers.observe(viewLifecycleOwner) { items ->
            items.let { adapter.submitList(items) }        // submit the LiveData<List<Weather>> to the RecyclerViews Adapter
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