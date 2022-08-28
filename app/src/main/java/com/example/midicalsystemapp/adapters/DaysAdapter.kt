package com.example.midicalsystemapp.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.midicalsystemapp.data.FakeDatabase
import com.example.midicalsystemapp.databinding.DayListItemBinding
import com.example.midicalsystemapp.utils.FilterListener
import com.example.midicalsystemapp.utils.FilterObservable
import com.example.midicalsystemapp.utils.UnSelectListener
import com.example.midicalsystemapp.utils.UnSelectObservable
import java.util.*

class DaysAdapter : RecyclerView.Adapter<DaysAdapter.ViewHolder>(), FilterObservable,
    UnSelectObservable,
    UnSelectListener,
    FilterListener {
    val days = createDaysMap()
    private var selected = -1
    private var monthSelected = Date().month
    private lateinit var listener: FilterListener
    private lateinit var unSelectListener: UnSelectListener
    private lateinit var holder: ViewHolder

    inner class ViewHolder(binding: DayListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val number = binding.number
        val name = binding.name

        init {
            itemView.setOnClickListener {
                selected = adapterPosition
                notifyDataSetChanged()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            DayListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var pos = position + 1
        val calender = Calendar.Builder().build()
        holder.number.text = pos.toString()

        if (pos % 7 == 0) {
            pos = 1
        } else if (pos % 7 > 0) {
            pos %= 7
        }

        holder.name.text = days.get(pos)
        if (selected == position) {
            holder.itemView.background.setTint(Color.CYAN)
            val day = position + 1
            registerListener(listener, "$monthSelected/$day")
            this.holder = holder
        } else {
            holder.itemView.background.setTint(Color.WHITE)
        }

    }


    override fun getItemCount(): Int {
        return 30
    }

    private fun createDaysMap(): Map<Int, String> {
        val map = mutableMapOf<Int, String>()
        map.put(1, "Sat")
        map.put(2, "Sun")
        map.put(3, "Mon")
        map.put(4, "Tue")
        map.put(5, "Wes")
        map.put(6, "Thu")
        map.put(7, "Fri")
        return map
    }

    override fun registerListener(listener: FilterListener, data: String) {
        listener.act(data)
    }

    fun setFilterListener(filterListener: FilterListener) {
        this.listener = filterListener
    }

    fun setUnselectListener(unSelectListener: UnSelectListener) {
        this.unSelectListener = unSelectListener
    }

    override fun act(data: String) {
        monthSelected = data.toInt()

    }

    override fun registerListener(listener: UnSelectListener) {
        unSelectListener.onUnSelect()
    }

    override fun onUnSelect() {
        if (selected > -1) {
            holder.itemView.background.setTint(Color.WHITE)
            selected = -1
            notifyDataSetChanged()
            registerListener(unSelectListener)
        }
    }


}