package com.example.weatherapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.api.Url
import com.example.weatherapp.model.dataclass.FindItem
import com.example.weatherapp.util.extention.toCelsius
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_weather.view.*

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var dataList = ArrayList<FindItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MainViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
    )

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        if (!dataList.isNullOrEmpty()) {
            holder.bind(dataList[position])
        }
    }

    override fun getItemCount() = dataList.size

    fun setData(data: ArrayList<FindItem>) {
        dataList = data
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        dataList.removeAt(position)
        notifyItemRemoved(position)
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ilvIconWeather = itemView.ivWeatherIcon
        private val tvCityName = itemView.tvCityName
        private val tvTemperature = itemView.tvTemperature

        fun bind(item: FindItem) {
            tvCityName.text = item.name
            tvTemperature.text = item.main?.temp!!.toCelsius()
            Picasso.get()
                .load(Url.IMAGE_BASE_URL + item.weather!![0].icon + ".png")
                .error(R.drawable.ic_error)
                .into(ilvIconWeather.imageView, object : Callback {
                    override fun onSuccess() {
                        ilvIconWeather.completeLoading()
                    }

                    override fun onError(e: Exception?) {
                        ilvIconWeather.completeLoading()
                    }
                })
        }
    }

}