package com.example.midicalsystemapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.midicalsystemapp.R
import com.example.midicalsystemapp.data.FakeDatabase
import com.example.midicalsystemapp.data.FakeRecord
import com.example.midicalsystemapp.databinding.MedicalRecordItemBinding
import com.example.midicalsystemapp.databinding.MedicationListItemBinding
import java.util.*

class MedicalRecordsAdapter(data: ArrayList<FakeRecord>) :
    RecyclerView.Adapter<MedicalRecordsAdapter.ViewHolder>() {
    val data: ArrayList<FakeRecord>

    init {
        this.data = data
    }

    class ViewHolder(binding: MedicalRecordItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val name = binding.name
        val time = binding.time
        val doctorName = binding.doctorName
        val clinic = binding.clinicName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            MedicalRecordItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val record = data.get(position)
        holder.name.text = record.name
        holder.doctorName.text = record.doctorName
        holder.time.text = record.time
    }

    override fun getItemCount(): Int {
        return data.size
    }


}