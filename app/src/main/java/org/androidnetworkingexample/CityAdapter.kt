package org.androidnetworkingexample

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_city.view.*

class CityAdapter(val cities : MutableList<Cities>) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_city,parent,false)
        return CityViewHolder(v)

    }

    override fun getItemCount(): Int {
        return cities.size
    }
        fun add(item:Cities, position:Int) {
            cities.add(position, item)
        notifyItemInserted(position)
    }

    fun remove(item:Cities) {
        val position = cities.indexOf(item)
        cities.removeAt(position)
        notifyItemRemoved(position)
    }
    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cities[position]
        holder.itemView.textViewCityName.text = city.cityName
        holder.itemView.textViewPlateCode.text = city.plateCode
        Picasso.get().load(city.imageUrl).into(holder.itemView.imgLogo)
        holder.itemView.setOnClickListener { Toast.makeText(holder.itemView.context,"${city.cityName}",Toast.LENGTH_SHORT).show() }
        holder.itemView.setOnLongClickListener {
            remove(city)
            return@setOnLongClickListener true
        }
    }


    class CityViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}