package com.ex.kotlinview.tabLayoutviewpager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class FragAdapter(fm: FragmentManager, val fragList: List<Fragment>, val titleList: List<String>) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return fragList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }
}