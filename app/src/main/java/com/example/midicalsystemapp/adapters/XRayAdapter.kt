package com.example.midicalsystemapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.midicalsystemapp.UI.Activities.PDFViwer
import com.example.midicalsystemapp.data.XRay
import com.example.midicalsystemapp.databinding.MedicalTestItemBinding

class XRayAdapter(data: ArrayList<XRay>) : RecyclerView.Adapter<XRayAdapter.ViewHolder>() {
    val data: ArrayList<XRay>

    init {
        this.data = data
    }

    inner class ViewHolder(binding: MedicalTestItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val title = binding.label
        val date = binding.date
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            MedicalTestItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data.get(position)
        holder.apply {
            title.text = item.label
            date.text = item.date
            itemView.setOnClickListener {
                itemView.context.startActivity(Intent(itemView.context, PDFViwer::class.java))
            }
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }


}