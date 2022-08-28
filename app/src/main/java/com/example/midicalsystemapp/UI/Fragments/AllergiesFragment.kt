package com.example.midicalsystemapp.UI.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.midicalsystemapp.App.AppClass
import com.example.midicalsystemapp.DBConnection.FirebaseConnection
import com.example.midicalsystemapp.Model.Allergy
import com.example.midicalsystemapp.adapters.AllergiesAdapter
import com.example.midicalsystemapp.adapters.MedicalTestsAdapter
import com.example.midicalsystemapp.data.FakeDatabase
import com.example.midicalsystemapp.databinding.AllergiesFragmentBinding
import com.example.midicalsystemapp.databinding.AllergyItemBinding

class AllergiesFragment : Fragment() {
    private lateinit var recycler: RecyclerView
    private lateinit var binding: AllergiesFragmentBinding
    private lateinit var db: FirebaseConnection
    private lateinit var adapter: AllergiesAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        db = FirebaseConnection()
        binding = AllergiesFragmentBinding.inflate(layoutInflater, container, false)
        recycler = binding.allergies
        adapter = context?.let { AllergiesAdapter(it) }!!
        val layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        recycler.layoutManager = layoutManager
        recycler.adapter = adapter
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (adapter.getUpdatedIndex() != -1) {
            adapter.data.set(adapter.getUpdatedIndex(), AppClass.allergy)
            adapter.notifyDataSetChanged()
        }
    }


}