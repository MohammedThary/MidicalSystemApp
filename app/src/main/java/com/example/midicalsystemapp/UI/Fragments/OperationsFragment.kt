package com.example.midicalsystemapp.UI.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.midicalsystemapp.App.AppClass
import com.example.midicalsystemapp.R
import com.example.midicalsystemapp.adapters.OperationsAdapter
import com.example.midicalsystemapp.data.FakeDatabase
import com.example.midicalsystemapp.databinding.FragmentOperationsBinding


class OperationsFragment : Fragment() {
    private lateinit var recycler: RecyclerView
    private lateinit var binding: FragmentOperationsBinding
    private lateinit var adapter: OperationsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOperationsBinding.inflate(layoutInflater, container, false)
        recycler = binding.operations
        adapter = OperationsAdapter()
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recycler.layoutManager = layoutManager
        recycler.adapter = adapter
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (adapter.getUpdatedIndex() != -1) {
            adapter.data.set(adapter.getUpdatedIndex(), AppClass.operation)
            adapter.notifyDataSetChanged()
        }

    }
}