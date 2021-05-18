package com.example.testfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CalendarAdapter(private var dayOfMonthArray: ArrayList<String>, private var onItemClick: OnItemCLick) : RecyclerView.Adapter<CalendarAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var dayOfMonth: TextView = itemView.findViewById(R.id.textViewCellDay)
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onItemClick.onItemClick(adapterPosition, dayOfMonth.toString())
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarAdapter.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.calendar_cell,parent,false)
        val layoutParams: ViewGroup.LayoutParams = view.layoutParams
        layoutParams.height = (parent.height*0.166666666).toInt()
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dayOfMonthArray.size
    }

    override fun onBindViewHolder(holder: CalendarAdapter.ViewHolder, position: Int) {
        holder.dayOfMonth.text = dayOfMonthArray[position]
    }

    interface OnItemCLick{
        fun onItemClick(position: Int, dayText: String)
    }

}