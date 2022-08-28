package com.example.midicalsystemapp.UI.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.midicalsystemapp.App.AppClass
import com.example.midicalsystemapp.Model.ChronicDisease
import com.example.midicalsystemapp.adapters.DiseasesAdapter
import com.example.midicalsystemapp.data.FakeDatabase
import com.example.midicalsystemapp.databinding.DiseasesFragmentBinding

class DiseasesFragment : Fragment() {
    private lateinit var binding: DiseasesFragmentBinding
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: DiseasesAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DiseasesFragmentBinding.inflate(layoutInflater)
        recycler = binding.recycler
        adapter = DiseasesAdapter()
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (adapter.getUpdatedIndex() != -1) {
            adapter.data.set(adapter.getUpdatedIndex(), AppClass.disease)
            adapter.notifyDataSetChanged()
        }
    }
}