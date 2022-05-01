package com.example.weatherforecast.view


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.R
import com.example.weatherforecast.database.Weather

class WeatherListAdapter: ListAdapter<Weather, WeatherListAdapter.WeatherViewHolder>(WeathersComparator()) {

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById(R.id.weather_id)
        val temp: TextView = itemView.findViewById(R.id.weather_temp)
        val summary: TextView = itemView.findViewById(R.id.weather_summary)

        companion object {
            fun create(parent: ViewGroup): WeatherViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_weather, parent, false)
                return WeatherViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {

        val weatherViewHolder:WeatherViewHolder = WeatherViewHolder.create(parent)

        weatherViewHolder.itemView.setOnLongClickListener {
            Toast.makeText(weatherViewHolder.itemView.context, "Long click detected", Toast.LENGTH_SHORT).show()
            return@setOnLongClickListener true
        }

        return weatherViewHolder
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val current = getItem(position)

        holder.id.text = current.id.toString()
        holder.temp.text = current.temp
        holder.summary.text = current.summary


    }




    class WeathersComparator : DiffUtil.ItemCallback<Weather>() {
        override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
            return oldItem.id == newItem.id
        }
    }
}