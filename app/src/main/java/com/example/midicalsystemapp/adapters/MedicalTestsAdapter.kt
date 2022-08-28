package com.example.midicalsystemapp.adapters

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.pdf.PdfDocument
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.Environment
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.midicalsystemapp.App.AppClass
import com.example.midicalsystemapp.UI.Activities.PDFViwer
import com.example.midicalsystemapp.data.FakeDatabase
import com.example.midicalsystemapp.data.MedicalTest
import com.example.midicalsystemapp.databinding.MedicalTestItemBinding
import java.io.File
import java.util.ArrayList

class MedicalTestsAdapter(data: ArrayList<MedicalTest>) :
    RecyclerView.Adapter<MedicalTestsAdapter.ViewHolder>() {
    val data: ArrayList<MedicalTest>


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
        val item = data[position]
        holder.title.text = item.name
        holder.date.text = item.date
        val context = holder.itemView.context
        val intent = Intent(holder.itemView.context, PDFViwer::class.java)
        // val intent = Intent(Intent.ACTION_VIEW, FakeDatabase.pdfuri)
        holder.itemView.setOnClickListener {
            FakeDatabase.pdfuri = item.uri
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


}
