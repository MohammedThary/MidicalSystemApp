package com.example.midicalsystemapp.UI.Fragments

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.midicalsystemapp.App.AppClass
import com.example.midicalsystemapp.DBConnection.FirebaseConnection
import com.example.midicalsystemapp.adapters.MedicationsAdapter
import com.example.midicalsystemapp.data.FakeDatabase


class DeleteBoxFragment : DialogFragment() {
    var itemIndex = 0
    private lateinit var adapter: MedicationsAdapter

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage("Do you want to delete medication").setTitle("Delete")
                .setPositiveButton("Delete",
                    DialogInterface.OnClickListener { _, _ ->
                        /*FakeDatabase.medications.removeAt(itemIndex)
                        itemIndex = 0*/
                        FirebaseConnection().deleteMedicine(AppClass.medicine.id)
                        adapter.data.removeAt(itemIndex)
                        adapter.notifyDataSetChanged()

                    })
                .setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { _, _ ->

                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    fun setAdapter(adapter: MedicationsAdapter) {
        this.adapter = adapter
    }


}