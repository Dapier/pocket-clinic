package com.example.pocketclinic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private val userList = ArrayList<citationData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.pacient_item,
            parent,false
        )
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = userList[position]

        holder.pacientName.text = currentitem.Name
        holder.typeService.text = currentitem.service
        holder.hourProgram.text = currentitem.hour

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun updateUserList(userList : List<citationData>){

        this.userList.clear()
        this.userList.addAll(userList)
        notifyDataSetChanged()

    }

    class  MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val pacientName : TextView = itemView.findViewById(R.id.tvfirstName)
        val typeService : TextView = itemView.findViewById(R.id.tvServices)
        val hourProgram : TextView = itemView.findViewById(R.id.tvhourProgram)

    }
}