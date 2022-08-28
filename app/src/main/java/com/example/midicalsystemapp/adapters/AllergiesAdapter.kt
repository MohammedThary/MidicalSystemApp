package com.example.midicalsystemapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.midicalsystemapp.App.AppClass
import com.example.midicalsystemapp.App.AppClass.allergy
import com.example.midicalsystemapp.DBConnection.FirebaseConnection
import com.example.midicalsystemapp.UI.Activities.UpdateAllergy
import com.example.midicalsystemapp.Model.Allergy
import com.example.midicalsystemapp.databinding.AllergyItemBinding
import com.example.midicalsystemapp.utils.AccessPoint
import java.util.ArrayList

class AllergiesAdapter(context: Context) :
    RecyclerView.Adapter<AllergiesAdapter.ViewHolder>() {
    private lateinit var db: FirebaseConnection
    var data: ArrayList<Allergy>
    var updatedItemIndex = -1
    val context: Context

    init {
        db = FirebaseConnection()
        this.data = AppClass.allergies
        this.context = context
        AccessPoint.allergyAdapter = this
    }

    class ViewHolder(binding: AllergyItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val name = binding.title
    }

    override
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            AllergyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.name.text = item.name
        val intent = Intent(context, UpdateAllergy::class.java)
        holder.itemView.setOnClickListener {
            updatedItemIndex = holder.adapterPosition
            allergy = item
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun getUpdatedIndex(): Int {
        return this.updatedItemIndex
    }

}