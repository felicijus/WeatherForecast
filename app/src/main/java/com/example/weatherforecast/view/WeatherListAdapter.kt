package com.example.weatherforecast.view


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.R
import com.example.weatherforecast.database.Weather


class WeatherListAdapter: ListAdapter<Weather, WeatherListAdapter.WeatherViewHolder>(WeathersComparator()) {

    private lateinit var weatherItemClickListener: OnItemClickListener
    private lateinit var weatherItemLongClickListener: OnItemLongClickListener



    class WeatherViewHolder(itemView: View, weatherItemClickListener: OnItemClickListener, weatherItemLongClickListener: OnItemLongClickListener) : RecyclerView.ViewHolder(itemView){

        val icon: TextView = itemView.findViewById(R.id.icon)

        val id: TextView = itemView.findViewById(R.id.id)
        val timestamp: TextView = itemView.findViewById(R.id.timestamp)

        val temperature: TextView = itemView.findViewById(R.id.temperature)
        val condition: TextView = itemView.findViewById(R.id.condition)
        val precipitation: TextView = itemView.findViewById(R.id.precipitation)

        init {
            itemView.setOnClickListener {
                weatherItemClickListener.setOnItemClickListener(adapterPosition)
            }

            itemView.setOnLongClickListener{
                weatherItemLongClickListener.setOnItemLongClickListener(adapterPosition)
                return@setOnLongClickListener true
            }
        }

        companion object {
            fun create(parent: ViewGroup, weatherItemClickListener: OnItemClickListener ,weatherItemLongClickListener: OnItemLongClickListener): WeatherViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_weather, parent, false)
                return WeatherViewHolder(view, weatherItemClickListener, weatherItemLongClickListener)
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {

        val weatherViewHolder:WeatherViewHolder = WeatherViewHolder.create(parent,weatherItemClickListener ,weatherItemLongClickListener)

        /*weatherViewHolder.itemView.setOnLongClickListener {
            Toast.makeText(weatherViewHolder.itemView.context, "Long click detected", Toast.LENGTH_SHORT).show()
            return@setOnLongClickListener true
        }*/

        return weatherViewHolder
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val current = getItem(position)

        holder.icon.text = current.icon.toString()

        holder.id.text = current.id.toString()
        holder.timestamp.text = current.timestamp.toString()

        holder.temperature.text = current.temperature.toString()
        holder.condition.text = current.condition.toString()
        holder.precipitation.text = current.precipitation.toString()

    }



    //ClickListener
    interface OnItemLongClickListener{
        fun setOnItemLongClickListener(position: Int)
    }
    fun setOnItemLongClickListener(itemLongClickListener: OnItemLongClickListener){
        this.weatherItemLongClickListener = itemLongClickListener
    }

    interface OnItemClickListener{
        fun setOnItemClickListener(position: Int)
    }
    fun setOnItemClickListener(itemClickListener: OnItemClickListener){
        this.weatherItemClickListener = itemClickListener
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