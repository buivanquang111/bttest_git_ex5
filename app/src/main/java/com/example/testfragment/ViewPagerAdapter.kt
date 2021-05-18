package com.example.testfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(var fm: FragmentManager, var list: List<DayOfMonth>, behavior: Int):
    FragmentStatePagerAdapter(fm, behavior) {


    override fun getItem(position: Int): Fragment {
        var str: DayOfMonth = list[position]
        val dateFragment: FragmentOne = FragmentOne()
        val bundle: Bundle = Bundle()
        bundle.putSerializable("string",str)
        dateFragment.arguments = bundle
        return dateFragment
    }

    override fun getCount(): Int {
        if (list != null) {
            return list.size
        }
        return 0
    }

}