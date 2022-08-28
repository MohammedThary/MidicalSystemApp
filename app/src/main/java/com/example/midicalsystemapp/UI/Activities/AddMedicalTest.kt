package com.example.midicalsystemapp.UI.Activities

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION
import android.util.Log
import androidx.core.app.ActivityCompat
import com.example.midicalsystemapp.DBConnection.FirebaseConnection
import com.example.midicalsystemapp.data.FakeDatabase
import com.example.midicalsystemapp.data.MedicalTest
import com.example.midicalsystemapp.databinding.AddMedicalTestBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.Timestamp
import java.util.*
import kotlin.random.Random

class AddMedicalTest : AppCompatActivity() {


    private lateinit var binding: AddMedicalTestBinding
    private val REQUEST_EXTERNAL_STORAGE = 1
    private val PERMISSION_STORAGE = arrayOf(
        READ_EXTERNAL_STORAGE,
        WRITE_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verifyStoragePermission(this)
        binding = AddMedicalTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .build()
        datePicker.apply {
            addOnPositiveButtonClickListener {
                binding.date.text = selection?.let { it1 ->
                    formatDate(Date(it1))
                }
            }
        }


        binding.date.setOnClickListener {
            datePicker.show(supportFragmentManager, "tag")
        }

        binding.uploadPdf.setOnClickListener {
            openFile()
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 2
            && resultCode == Activity.RESULT_OK && data != null &&
            data.data != null
        ) {
            FakeDatabase.pdfuri = data.data!!
            val contentResolver = applicationContext.contentResolver
            val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION or
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION
            contentResolver.takePersistableUriPermission(data.data!!, takeFlags)
            Log.e(Log.ERROR.toString(), FakeDatabase.pdfuri.toString())
            //  FirebaseConnection().uploadMedicalTest(data.data!!)
            /*   var medTest = com.example.midicalsystemapp.Model.MedicalTest()
               medTest.title = title.toString()
               medTest.created_at = Timestamp.now()
               medTest.uri = data.data!!
               FirebaseConnection().storeMedicalTest(medTest)*/
            FakeDatabase.medicalTests.add(
                MedicalTest(
                    Random(123).nextInt(),
                    binding.title.text.toString(),
                    binding.date.text.toString(),
                    FakeDatabase.pdfuri
                )
            )

            finish()
        }

    }

    private fun openFile() {

        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            type = "application/pdf"
        }
        startActivityForResult(intent, 2)
    }


    private fun verifyStoragePermission(activity: Activity) {

        val permission = ActivityCompat.checkSelfPermission(activity, WRITE_EXTERNAL_STORAGE)
        if (SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager() && permission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    activity,
                    PERMISSION_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
                );
                val intent = Intent()
                val uri = Uri.fromParts("package", this.packageName, null);
                intent.apply {
                    action = ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION
                    data = uri

                }
                startActivity(intent)
            }
        }
    }

    private fun formatDate(date: Date): String {
        val month = date.month.toString()
        val day = date.day.toString()
        val year = date.year.toString()
        return "$day/$month/$year"
    }
}





