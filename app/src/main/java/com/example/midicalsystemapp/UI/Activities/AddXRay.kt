package com.example.midicalsystemapp.UI.Activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.midicalsystemapp.DBConnection.FirebaseConnection
import com.example.midicalsystemapp.data.FakeDatabase
import com.example.midicalsystemapp.data.XRay
import com.example.midicalsystemapp.databinding.AddXrayBinding
import kotlin.random.Random

class AddXRay : AppCompatActivity() {
    private lateinit var binding: AddXrayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddXrayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.uploadPdf.setOnClickListener {
            openFile()
        }


    }

    private fun openFile() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "application/pdf"
        startActivityForResult(intent, 2)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null && requestCode == 2) {
            val uri = data.data
            FirebaseConnection().uploadMedicalTest(data.data!!)
            FakeDatabase.pdfuri = data.data!!
            val contentResolver = applicationContext.contentResolver

            val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION or
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION
            contentResolver.takePersistableUriPermission(data.data!!, takeFlags)
            uri?.let {
                XRay(
                    Random(10033434).nextInt(),
                    binding.title.text.toString(),
                    "",
                    it,
                )
            }?.let {
                FakeDatabase.xray.add(
                    it
                )
            }
            finish()
        }
    }
}