package com.example.midicalsystemapp.UI.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.midicalsystemapp.App.AppClass
import com.example.midicalsystemapp.Model.Medicine
import com.example.midicalsystemapp.UI.Activities.AddMedication
import com.example.midicalsystemapp.adapters.MedicationsAdapter
import com.example.midicalsystemapp.data.FakeDatabase
import com.example.midicalsystemapp.databinding.MedicationsBinding

class Medications : Fragment() {
    private lateinit var binding: MedicationsBinding

    companion object {
        private lateinit var adapter: MedicationsAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = MedicationsAdapter(childFragmentManager)
        binding = MedicationsBinding.inflate(layoutInflater)
        val intent = Intent(binding.root.context, AddMedication::class.java)
        binding.add.setOnClickListener {
            binding.root.context.startActivity(intent)
        }
        val medications = binding.medications
        val medicationsAdapter =
            activity?.let { MedicationsAdapter(childFragmentManager) }
        val verticalLayoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        medications.apply {
            adapter = medicationsAdapter
            layoutManager = verticalLayoutManager

        }
        return binding.root
    }


    override fun onResume() {
        super.onResume()
        /*if (adapter.getUpdatedIndex() != -1) {
            adapter.data.set(adapter.getUpdatedIndex(), AppClass.medicine)
            adapter.notifyDataSetChanged()
        }*/
    }
}