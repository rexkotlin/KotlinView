package com.ex.kotlinview.bottomnavigation

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ex.kotlinview.R
import com.ex.kotlinview.tabLayoutviewpager.*
import kotlinx.android.synthetic.main.tablayout_viewpager_fragment.view.*


/**
 * A simple [Fragment] subclass.
 *
 */
class TabLayoutViewPagerFragment : Fragment() {

    lateinit var mview: View

    private lateinit var adapter: ItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mview = inflater.inflate(R.layout.tablayout_viewpager_fragment, container, false)

        setupView()

        return mview


    }

    private fun setupView() {
        Log.d("TabLayoutViewPager", "setupView:");
        val titleList = arrayListOf<String>("durian", "grape", "honeydew melon", "lemon", "orange", "apple")
//        val fragList = arrayListOf<Fragment>(FragLazyInitTest(),FragLazyInitTest(),FragLazyInitTest(),FragLazyInitTest(),FragLazyInitTest())
        val fragList = arrayListOf<Fragment>(
            Park1Fragment(), Park2Fragment(), Park3Fragment(), Park4Fragment(), Park5Fragment(), Park6Fragment()
        )
        adapter = ItemsAdapter(childFragmentManager, fragList, titleList)
        mview!!.layout_main_viewPager.adapter = adapter
        mview!!.layout_main_tabLayout.setupWithViewPager(mview!!.layout_main_viewPager)
    }
}
