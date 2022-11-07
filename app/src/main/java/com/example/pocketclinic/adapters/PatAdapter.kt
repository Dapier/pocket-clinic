package com.example.pocketclinic.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketclinic.R
import com.example.pocketclinic.models.PatientModel

class PatAdapter (private val patList: ArrayList<PatientModel>):
    RecyclerView.Adapter<PatAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.pat_list_item, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: PatAdapter.ViewHolder, position: Int) {
        val currentPat = patList[position]
        holder.tvPatName.text = currentPat.patNombre
    }

    override fun getItemCount(): Int {
        return patList.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val tvPatName : TextView = itemView.findViewById(R.id.tvPatName)

        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }

    }

}