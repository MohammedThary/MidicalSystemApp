package com.example.midicalsystemapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.midicalsystemapp.App.AppClass
import com.example.midicalsystemapp.DBConnection.FirebaseConnection
import com.example.midicalsystemapp.Model.MedicineSchedule
import com.example.midicalsystemapp.databinding.SheduleItemBinding
import com.example.midicalsystemapp.utils.AccessPoint
import com.example.midicalsystemapp.utils.FilterListener
import com.example.midicalsystemapp.utils.UnSelectListener


class MedicationShceduleAdapter(/*data: ArrayList<MedicationShcedule>*/) :
    RecyclerView.Adapter<MedicationShceduleAdapter.ViewHolder>(), FilterListener, UnSelectListener {

    //  val data: ArrayList<MedicationShcedule>
    var data: ArrayList<MedicineSchedule>

    init {
        //getMedicalTests();
        this.data = AppClass.medicineSchedules
        AccessPoint.medicationShceduleAdapter = this
    }

    inner class ViewHolder(binding: SheduleItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val name = binding.name
        val checked = binding.checkBox
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            SheduleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        /*  val item = data[position]
          val med = FakeDatabase.getMedById(item.medId)
          val date = LocalDateTime.now()
          val itemDate = item.date
          val arr = itemDate.split("/")
          val month = arr[0].toInt()
          val day = arr[1].toInt()
          if (med != null) {
              holder.name.text = med.name
          }
          holder.checked.isChecked = item.checked*/
        val item = data[position]
         for (v in AppClass.medications) {
             if (v.id == item.medId) {
                 holder.name.text = v.name
                 holder.checked.isChecked = item.isChecked
                 break
             }
    }
}

override fun getItemCount(): Int {
    return data.size
}

override fun act(data: String) {

    FirebaseConnection().getMedicineSchedule(data)

}

override fun onUnSelect() {
    // storeCurrentDate()
}

/*  fun storeCurrentDate() {
      val list = ArrayList<MedicationShcedule>()
      val day = Calendar.DAY_OF_MONTH
      val month = Calendar.MONTH
      for (v in FakeDatabase.medicationShceduleList) {
          if (v.date == "$month/$day") {
              list.add(v)
              println(v.id)
          }
      }
      data.clear()
      data.addAll(list)
      notifyDataSetChanged()
  }*/

}