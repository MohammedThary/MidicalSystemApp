package com.example.midicalsystemapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.midicalsystemapp.R
import com.example.midicalsystemapp.data.FakeDatabase
import com.example.midicalsystemapp.databinding.MedicalRecordItemBinding
import com.example.midicalsystemapp.databinding.MedicalRecordsBinding
import com.example.midicalsystemapp.databinding.RecordsFilterItemBinding
import com.example.midicalsystemapp.utils.FilterListener
import com.example.midicalsystemapp.utils.FilterObservable
import java.util.*

class MedicalRecordsFilterAdapter(/*adapter: MedicalRecordsAdapter*/) :
    RecyclerView.Adapter<MedicalRecordsFilterAdapter.ViewHolder>(), FilterObservable {
    val values = getFilters()
    private lateinit var listener: FilterListener

    class ViewHolder(binding: RecordsFilterItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val label = binding.label
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RecordsFilterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.label.text = getFilters().get(position)
        holder.itemView.setOnClickListener {
            val txt = holder.label.text
            registerListener(listener, txt as String)
        }
    }

    override fun getItemCount(): Int {
        return getFilters().size
    }


    private fun getFilters(): ArrayList<String> {
        val values = ArrayList<String>()
        values.add("Diseases")
        values.add("Allergies")
        values.add("Operations")
        values.add("Medical Tests")
        values.add("XRay")
        return values
    }

    override fun registerListener(listener: FilterListener, data: String) {
        listener.act(data)
    }

    fun setListener(filterListener: FilterListener) {
        this.listener = filterListener
    }
}