package com.example.weatherforecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.weatherforecast.database.Weather
import com.example.weatherforecast.databinding.FragmentSecondBinding
import com.example.weatherforecast.model.WeatherViewModel
import com.example.weatherforecast.model.WeatherViewModelFactory
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    private val weatherViewModel: WeatherViewModel by activityViewModels {
        WeatherViewModelFactory((activity?.application as WeathersApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.buttonDeleteAll.setOnClickListener {
            weatherViewModel.deleteAll()
        }

        binding.buttonFetch.setOnClickListener {
            val url = "https://api.brightsky.dev/weather?lat=52&lon=7.6&date=2022-05-17"

            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET, url, null,
                { response ->

                    val weather: JSONArray = response.getJSONArray("weather")
                    for (i in 0 until weather.length()) {

                        try {
                            val oneObject: JSONObject = weather.getJSONObject(i)
                            // Pulling items from the array

                            val timestamp = oneObject.getString("timestamp")

                            binding.textviewSecond.text =
                                "Response: %s".format(timestamp.toString())
                            weatherViewModel.insert(
                                Weather(
                                    null,
                                    oneObject.getString("temperature").toInt(),
                                    oneObject.getString("condition").toString()
                                )
                            )

                        } catch (e: JSONException) {
                            // Oops
                        }
                    }
                },
                { error ->
                    binding.textviewSecond.text = error.toString()
                }
            )
            val requestQueue: RequestQueue = Volley.newRequestQueue(requireContext())
            requestQueue.add(jsonObjectRequest)

            requestQueue.start()
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}