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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

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

        // Fetch Online Data Button
        binding.buttonFetch.setOnClickListener {

            val currentDateTime = LocalDateTime.now()

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val formattedDateTime = currentDateTime.format(formatter)

            // Location based Weather Data Service, hardcoded near my Home
            // lat=50.9145917 lon=14.1342216
            // url = "https://api.brightsky.dev/weather?lat=50.9145917&lon=14.1342216&date=2022-05-19"
            val url =
                "https://api.brightsky.dev/weather?lat=50.9145917&lon=14.1342216&date=$formattedDateTime"


            // Demonstration why working with Room is usually very simple once set up
            // Only the dataclass constructor has to be called to append Data
            // Please use Android Studio Database Inspector to view the whole Dataset
            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET, url, null,
                { response ->

                    val weather: JSONArray = response.getJSONArray("weather")
                    for (i in 0 until weather.length()) {

                        try {
                            val oneObject: JSONObject = weather.getJSONObject(i)
                            // Pulling items from the array

                            binding.textviewSecond.text =
                                "Response: %s".format((response.getJSONArray("sources") as JSONArray).getJSONObject(0).optString("last_record"))
                            weatherViewModel.insert(
                                Weather(
                                    id = null,

                                    timestamp = oneObject.optString("timestamp"),
                                    source_id = oneObject.optInt("source_id"),
                                    precipitation = oneObject.optString("precipitation"),
                                    pressure_msl = oneObject.optDouble("pressure_msl"),
                                    sunshine = oneObject.optInt("sunshine"),
                                    temperature = oneObject.optDouble("temperature"),
                                    wind_direction = oneObject.optInt("wind_direction"),
                                    wind_speed = oneObject.optDouble("wind_speed"),
                                    cloud_cover = oneObject.optInt("cloud_cover"),
                                    dew_point = oneObject.optDouble("dew_point"),
                                    relative_humidity = oneObject.optInt("relative_humidity"),
                                    visibility = oneObject.optInt("visibility"),
                                    wind_gust_direction = oneObject.optInt("wind_gust_direction"),
                                    wind_gust_speed = oneObject.optDouble("wind_gust_speed"),

                                    condition = oneObject.optString("condition"),
                                    icon = oneObject.optString("icon")
                                )
                            )

                        } catch (e: JSONException) {
                            // Oops
                            binding.textviewSecond.text = e.message
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