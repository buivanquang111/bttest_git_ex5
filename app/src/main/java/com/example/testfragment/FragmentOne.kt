package com.example.testfragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_one.*
import kotlinx.android.synthetic.main.fragment_one.view.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FragmentOne : Fragment(),CalendarAdapter.OnItemCLick {
    lateinit var mMainActivity: MainActivity
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_one,container,false);
            mMainActivity = activity as MainActivity
        var bundle: Bundle? = arguments
        var dayOfMonth: DayOfMonth = bundle?.getSerializable("string") as DayOfMonth

        mMainActivity.textViewName.text = monthYearFromDate(dayOfMonth.selectLocalDate)
        var calendarAdapter: CalendarAdapter = CalendarAdapter(dayOfMonth.list,this)
        var layoutManager: GridLayoutManager = GridLayoutManager(context,7)
        view.recyclerView.addItemDecoration(
            DividerItemDecoration(context,
                DividerItemDecoration.HORIZONTAL)
        )
        view.recyclerView.layoutManager = layoutManager
        view.recyclerView.adapter = calendarAdapter


        return view
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun monthYearFromDate(date: LocalDate): String{
        var formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date.format(formatter)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onItemClick(position: Int, dayText: String) {
        Toast.makeText(context,"select date "+dayText+" "+monthYearFromDate(mMainActivity.selectDate), Toast.LENGTH_SHORT).show()

    }
}