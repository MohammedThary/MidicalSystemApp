package com.example.midicalsystemapp.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.midicalsystemapp.databinding.MonthListItemBinding
import com.example.midicalsystemapp.utils.FilterListener
import com.example.midicalsystemapp.utils.FilterObservable
import com.example.midicalsystemapp.utils.UnSelectListener
import com.example.midicalsystemapp.utils.UnSelectObservable

class MonthsAdapter : RecyclerView.Adapter<MonthsAdapter.ViewHolder>(), FilterObservable,
    UnSelectObservable {

    val months: Map<Int, String>
    private var selected = -1
    private lateinit var listener: FilterListener
    private lateinit var unSelectListener: UnSelectListener

    init {
        months = createMonthsMap()
    }

    inner class ViewHolder(binding: MonthListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val text = binding.text
        var color = Color.WHITE

        init {
            itemView.setOnClickListener {
                if (selected == adapterPosition) {
                    itemView.background.setTint(Color.WHITE)
                    selected = -1
                    notifyDataSetChanged()
                    registerListener(unSelectListener)
                }
                selected = adapterPosition
                notifyDataSetChanged()

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            MonthListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = months[position]
        if (selected == position) {
            holder.itemView.background.setTint(Color.CYAN)
            val month = position + 1
            registerListener(listener, month.toString())
        } else {
            holder.itemView.background.setTint(Color.WHITE)
        }

    }

    override fun getItemCount(): Int {
        return 12
    }

    private fun createMonthsMap(): Map<Int, String> {
        val map = mutableMapOf<Int, String>()
        map[0] = "Jan"
        map[1] = "Feb"
        map[2] = "Mar"
        map[3] = "Apr"
        map[4] = "May"
        map[5] = "Jun"
        map[6] = "Jul"
        map[7] = "Aug"
        map[8] = "Sep"
        map[9] = "Oct"
        map[10] = "Nov"
        map[11] = "Dec"

        return map
    }

    override fun registerListener(listener: FilterListener, data: String) {
        listener.act(data)
    }

    fun setFilterListener(listener: FilterListener) {
        this.listener = listener
    }

    fun setUnselectListener(listener: UnSelectListener) {
        this.unSelectListener = listener
    }


    override fun registerListener(listener: UnSelectListener) {
        listener.onUnSelect()
    }


}