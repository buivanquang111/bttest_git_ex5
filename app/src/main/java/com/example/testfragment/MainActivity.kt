package com.example.testfragment

import android.R.attr.fragment
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter


class MainActivity : AppCompatActivity(){

     var curentPosition: Int = 0
    @RequiresApi(Build.VERSION_CODES.O)
    public var selectDate: LocalDate = LocalDate.now()
    private lateinit var mList: ArrayList<DayOfMonth>
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mList = getList()
        var adapter: ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager,mList,
        FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        viewpager.adapter = adapter

        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            var currentPage : Int = 0
            var mPreviousPosition : Int = 0
            var mIsEndOfCycle = false
            override fun onPageScrollStateChanged(state: Int) {
                /*val pageCount = viewpager?.adapter?.count
                if (state == ViewPager.SCROLL_STATE_IDLE){
                    if (mPreviousPosition == currentPage && !mIsEndOfCycle){
                        pageCount?.minus(1)?.let { viewpager.setCurrentItem(it,false) }
                    }else{
                        viewpager?.setCurrentItem(0,false)
                    }
                    mIsEndOfCycle = true
                }else{
                    mIsEndOfCycle=false
                }
                mPreviousPosition =currentPage*/

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                if (curentPosition<position){
                    selectDate = selectDate.plusMonths(1)
                }
                else if (curentPosition> position){
                    selectDate =selectDate.minusMonths(1)
                }
                curentPosition = position
                //currentPage = position
            }
        })
        imageViewBack.setOnClickListener {
            viewpager.currentItem = viewpager.currentItem -1
        }
        imageViewNext.setOnClickListener {
            viewpager.currentItem = viewpager.currentItem+1
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun getList(): ArrayList<DayOfMonth>{
       var list: ArrayList<DayOfMonth> = ArrayList()

        for (i in 0 until 12){
            list.add(DayOfMonth(dayInMonthArray(selectDate),selectDate))
        }
        return list
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun dayInMonthArray(date: LocalDate): ArrayList<String>{
        var daysInMonthArray: ArrayList<String> = ArrayList()
        var yearMonth: YearMonth = YearMonth.from(date)
        var daysInMonth = yearMonth.lengthOfMonth()
        var firstOfMonth=selectDate.withDayOfMonth(1)
        var dayOfWeek = firstOfMonth.dayOfWeek.value
        for (i in 1 until 43){
            when{
                (i <= dayOfWeek) -> {
                    daysInMonthArray.add((i-dayOfWeek+daysInMonth).toString())
                }
                (i> daysInMonth+dayOfWeek) ->{
                    daysInMonthArray.add((i-daysInMonth-dayOfWeek).toString())
                }
                else ->{
                    daysInMonthArray.add((i-dayOfWeek).toString())
                }
            }
        }
        return daysInMonthArray
    }

}