package com.example.midicalsystemapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.midicalsystemapp.App.AppClass
import com.example.midicalsystemapp.UI.Activities.UpdateOperations
import com.example.midicalsystemapp.Model.PreviousOperation
import com.example.midicalsystemapp.databinding.OpertationsItemBinding
import com.example.midicalsystemapp.utils.AccessPoint

class OperationsAdapter() :
    RecyclerView.Adapter<OperationsAdapter.ViewHolder>() {
    var data: ArrayList<PreviousOperation>
    private var updateIndex = -1

    init {
        this.data = AppClass.operations;
        AccessPoint.operationsAdapter = this
    }

    inner class ViewHolder(binding: OpertationsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name = binding.label
        val hospital = binding.hospital
        val date = binding.time


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            OpertationsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data.get(position)
        holder.apply {
            itemView.setOnClickListener {
                updateIndex = holder.adapterPosition
                AppClass.operation = item
                itemView.context.startActivity(
                    Intent(
                        itemView.context,
                        UpdateOperations::class.java
                    )
                )
            }
            name.text = item.operationName
            hospital.text = item.clinicName
            if (item.date != null) {
                date.text = item.date.toDate().toString()
            }

        }
    }

    fun getUpdatedIndex(): Int {
        return this.updateIndex
    }

    override fun getItemCount(): Int {
        return data.size
    }

}