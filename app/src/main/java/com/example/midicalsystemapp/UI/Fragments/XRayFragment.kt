package com.example.midicalsystemapp.UI.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.midicalsystemapp.adapters.XRayAdapter
import com.example.midicalsystemapp.data.FakeDatabase
import com.example.midicalsystemapp.databinding.FragmentXRayBinding


class XRayFragment : Fragment() {
    private lateinit var binding: FragmentXRayBinding
    private lateinit var recycler: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentXRayBinding.inflate(layoutInflater)
        recycler = binding.recycler
        recycler.adapter = XRayAdapter(FakeDatabase.xray)
        recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        recycler.adapter?.notifyDataSetChanged()
    }

}