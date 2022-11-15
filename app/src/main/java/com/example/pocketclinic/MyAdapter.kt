package com.example.pocketclinic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketclinic.ui.recolectionData


class MyAdapter(private val userList : ArrayList<recolectionData>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.pacient_item,
            parent, false
        )
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = userList[position]

        holder.name.text = currentitem.name
        holder.service.text = currentitem.service
        holder.hour.text = currentitem.hour

    }

    override fun getItemCount(): Int {
        return userList.size
    }



    class  MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val name : TextView = itemView.findViewById(R.id.tvfirstName)
        val service : TextView = itemView.findViewById(R.id.tvServices)
        val hour : TextView = itemView.findViewById(R.id.tvhourProgram)

    }
}