package com.example.midicalsystemapp.UI.Activities

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.midicalsystemapp.R
import com.example.midicalsystemapp.UI.Fragments.*
import com.example.midicalsystemapp.adapters.MedicalRecordsFilterAdapter
import com.example.midicalsystemapp.databinding.MedicalRecordsBinding
import com.example.midicalsystemapp.utils.FilterListener


class MedicalRecords : Fragment(), FilterListener {
    private lateinit var binding: MedicalRecordsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MedicalRecordsBinding.inflate(layoutInflater)

        initRecordsFilterRecycler()
        setClickListeners(binding)
        val filterObservable = binding.filter.adapter as MedicalRecordsFilterAdapter
        filterObservable.setListener(this)
        return binding.root
    }


    private fun showButtonInfoDialog(txt: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        builder.setMessage("This button adds $txt")
            .setTitle("Button Info")
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun initRecordsFilterRecycler() {
        val filterRecycler = binding.filter
        val layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        val adapter = MedicalRecordsFilterAdapter()
        filterRecycler.adapter = adapter
        filterRecycler.layoutManager = layoutManager

    }

    private fun showAddButtons(binding: MedicalRecordsBinding) {
        val addDisease = binding.addDiseaseBtn
        val addAllergy = binding.addAllergyBtn
        val addMedicalTest = binding.addMedicalTest
        val addOperation = binding.addOperation
        val addXRay = binding.addXrayBtn
        val reveal = AnimatorSet()
        val animator = ObjectAnimator.ofFloat(addAllergy, "scaleX", +0.8f).apply {
            duration = 300

        }
        val animator2 = ObjectAnimator.ofFloat(addAllergy, "scaleY", +0.8f).apply {
            duration = 300

        }

        val animato3 = ObjectAnimator.ofFloat(addDisease, "scaleX", +0.8f).apply {
            duration = 300

        }
        val animato4 = ObjectAnimator.ofFloat(addDisease, "scaleY", +0.8f).apply {
            duration = 300

        }
        val animato5 = ObjectAnimator.ofFloat(addMedicalTest, "scaleX", +0.8f).apply {
            duration = 300

        }
        val animato6 = ObjectAnimator.ofFloat(addMedicalTest, "scaleY", +0.8f).apply {
            duration = 300

        }
        val animato7 = ObjectAnimator.ofFloat(addOperation, "scaleX", +0.8f).apply {
            duration = 300

        }
        val animato8 = ObjectAnimator.ofFloat(addOperation, "scaleY", +0.8f).apply {
            duration = 300

        }
        val animato9 = ObjectAnimator.ofFloat(addXRay, "scaleX", +0.8f).apply {
            duration = 300

        }

        val animato10 = ObjectAnimator.ofFloat(addXRay, "scaleY", +0.8f).apply {
            duration = 300

        }

        reveal.apply {
            play(animator).with(animator2).with(animato3).with(animato4).with(animato5)
                .with(animato6).with(animato7).with(animato8).with(animato9).with(animato10)
            start()

        }

    }

    private fun collapseAddButtons(binding: MedicalRecordsBinding) {
        val addDisease = binding.addDiseaseBtn
        val addAllergy = binding.addAllergyBtn
        val addMedicalTest = binding.addMedicalTest
        val addOperation = binding.addOperation
        val addXRay = binding.addXrayBtn
        val hide = AnimatorSet()
        val animator = ObjectAnimator.ofFloat(addAllergy, "scaleX", +0.0f).apply {
            duration = 300

        }
        val animator2 = ObjectAnimator.ofFloat(addAllergy, "scaleY", +0.0f).apply {
            duration = 300

        }

        val animato3 = ObjectAnimator.ofFloat(addDisease, "scaleX", +0.0f).apply {
            duration = 300

        }
        val animato4 = ObjectAnimator.ofFloat(addDisease, "scaleY", +0.0f).apply {
            duration = 300

        }
        val animato5 = ObjectAnimator.ofFloat(addMedicalTest, "scaleX", +0.0f).apply {
            duration = 300
        }
        val animato6 = ObjectAnimator.ofFloat(addMedicalTest, "scaleY", +0.0f).apply {
            duration = 300
        }

        val animato7 = ObjectAnimator.ofFloat(addOperation, "scaleX", +0.0f).apply {
            duration = 300
        }
        val animato8 = ObjectAnimator.ofFloat(addOperation, "scaleY", +0.0f).apply {
            duration = 300
        }

        val animato9 = ObjectAnimator.ofFloat(addXRay, "scaleX", +0.0f).apply {
            duration = 300

        }
        val animato10 = ObjectAnimator.ofFloat(addXRay, "scaleY", +0.0f).apply {
            duration = 300

        }
        hide.apply {
            play(animator).with(animator2).with(animato3).with(animato4).with(animato5)
                .with(animato6).with(animato7).with(animato8).with(animato9).with(animato10)
            start()
        }
    }

    private fun setClickListeners(binding: MedicalRecordsBinding) {
        val addOperationIntent = Intent(binding.root.context, AddOperation::class.java)
        val addMedTestIntent = Intent(binding.root.context, AddMedicalTest::class.java)
        val addAllergyIntent = Intent(binding.root.context, AddAllergy::class.java)
        val addDiseaseIntent = Intent(binding.root.context, AddDisease::class.java)
        val addXRayIntent = Intent(binding.root.context, AddXRay::class.java)
        binding.addBtn.setOnClickListener {
            if (binding.addAllergyBtn.scaleX == 0.8f) {
                collapseAddButtons(binding)
            } else {
                showAddButtons(binding)
            }
        }
        binding.addOperation.setOnClickListener {
            startActivity(addOperationIntent)
        }
        binding.addAllergyBtn.setOnClickListener {
            startActivity(addAllergyIntent)
        }

        binding.addMedicalTest.setOnClickListener {
            startActivity(addMedTestIntent)
        }
        binding.addDiseaseBtn.setOnClickListener {
            startActivity(addDiseaseIntent)
        }
        binding.addXrayBtn.setOnClickListener {
            startActivity(addXRayIntent)
        }


    }


    override fun act(data: String) {
        when (data) {
            "Diseases" -> childFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DiseasesFragment::class.java, null)
                .setReorderingAllowed(true)
                .addToBackStack("") // name can be null
                .commit();
            "Allergies" -> childFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AllergiesFragment::class.java, null)
                .setReorderingAllowed(true)
                .addToBackStack("") // name can be null
                .commit();
            "Medical Tests" -> childFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MedicalTests::class.java, null)
                .setReorderingAllowed(true)
                .addToBackStack("") // name can be null
                .commit();

            "Operations" -> childFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, OperationsFragment::class.java, null)
                .setReorderingAllowed(true)
                .addToBackStack("") // name can be null
                .commit();
            "XRay" -> childFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, XRayFragment::class.java, null)
                .setReorderingAllowed(true)
                .addToBackStack("") // name can be null
                .commit();
            /* supportFragmentManager.beginTransaction()
                   .replace(R.id.fragment_container, FollowingDoctors::class.java, null)
                   .setReorderingAllowed(true)
                   .addToBackStack("") // name can be null
                   .commit();*/
        }
    }

    fun onUnselect() {
        TODO("Not yet implemented")
    }


}