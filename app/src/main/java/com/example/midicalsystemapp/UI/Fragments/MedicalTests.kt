package com.example.midicalsystemapp.UI.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.midicalsystemapp.adapters.MedicalTestsAdapter
import com.example.midicalsystemapp.data.FakeDatabase
import com.example.midicalsystemapp.databinding.MedicalTestsFragmentBinding

class MedicalTests : Fragment() {
    private lateinit var recycler: RecyclerView
    private lateinit var binding: MedicalTestsFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MedicalTestsFragmentBinding.inflate(layoutInflater, container, false)
        recycler = binding.recycler
        val adapter = MedicalTestsAdapter(FakeDatabase.medicalTests)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recycler.layoutManager = layoutManager
        recycler.adapter = adapter
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        recycler.adapter?.notifyDataSetChanged()
    }
}