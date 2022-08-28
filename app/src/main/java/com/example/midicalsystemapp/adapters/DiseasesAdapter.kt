package com.example.midicalsystemapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.midicalsystemapp.App.AppClass
import com.example.midicalsystemapp.UI.Activities.UpdateDisease
import com.example.midicalsystemapp.Model.ChronicDisease
import com.example.midicalsystemapp.databinding.DiseaseItemBinding
import com.example.midicalsystemapp.utils.AccessPoint

class DiseasesAdapter() :
    RecyclerView.Adapter<DiseasesAdapter.ViewHolder>() {
    val data: ArrayList<ChronicDisease>
    private var updatedIndex = -1

    init {
        this.data = AppClass.diseases
        AccessPoint.diseasesAdapter = this
    }

    inner class ViewHolder(binding: DiseaseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val title = binding.label
        val medicine = binding.medicine
        val date = binding.date

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            DiseaseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data.get(position)
        holder.apply {
            title.text = item.name
            medicine.text = item.medicineName
            date.text = item.created_at.toDate().toString()
        }
        holder.itemView.setOnClickListener {
            updatedIndex = holder.adapterPosition
            AppClass.disease = item
            val intent = Intent(holder.itemView.context, UpdateDisease::class.java)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun getUpdatedIndex(): Int {
        return this.updatedIndex
    }


}