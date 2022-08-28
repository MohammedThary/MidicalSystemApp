package com.example.midicalsystemapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.midicalsystemapp.App.AppClass
import com.example.midicalsystemapp.DBConnection.FirebaseConnection
import com.example.midicalsystemapp.UI.Fragments.DeleteBoxFragment
import com.example.midicalsystemapp.UI.UpdateMedication
import com.example.midicalsystemapp.Model.Medicine
import com.example.midicalsystemapp.databinding.MedicationListItem2Binding
import com.example.midicalsystemapp.utils.AccessPoint

class MedicationsAdapter(fragmentManager: FragmentManager) :
    RecyclerView.Adapter<MedicationsAdapter.ViewHolder>() {
    var data: ArrayList<Medicine>
    private val fragmentManager: FragmentManager
    private val dialog: DeleteBoxFragment
    private var updatedItemIndex = -1

    init {
        this.data = AppClass.medications
        this.fragmentManager = fragmentManager
        dialog = DeleteBoxFragment()
        dialog.setAdapter(this)
        AccessPoint.medicationAdapter = this
    }

    class ViewHolder(binding: MedicationListItem2Binding) : RecyclerView.ViewHolder(binding.root) {
        val name = binding.name
        val time = binding.time
        val quantity = binding.quantity
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MedicationListItem2Binding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataItem = data[position]

        holder.apply {
            name.text = dataItem.name
            time.text = dataItem.firstTime.toDate().toString()
            quantity.text = dataItem.amount.toString() + " pills"

            itemView.setOnClickListener {
                updatedItemIndex = holder.adapterPosition
                println("***************** $updatedItemIndex")
                AppClass.medicine = dataItem
                val intent = Intent(holder.itemView.context, UpdateMedication::class.java)
                holder.itemView.context.startActivity(intent)
            }
            itemView.setOnLongClickListener {
                AppClass.medicine = dataItem
                dialog.show(fragmentManager, "del")
                dialog.itemIndex = position
                true
            }

        }

    }

    fun getUpdatedIndex(): Int {
        return this.updatedItemIndex
    }


    override fun getItemCount(): Int {
        return data.size
    }
}