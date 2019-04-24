package com.ex.kotlinview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.view.MenuItem
import com.ex.kotlinview.bottomnavigation.Fragment_2
import com.ex.kotlinview.bottomnavigation.TabLayoutViewPagerFragment
import com.ex.kotlinview.bottomnavigation.RecyclerViewFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var transaction: FragmentTransaction
    lateinit var recyclerViewFragment: RecyclerViewFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerViewFragment = supportFragmentManager.findFragmentByTag("fragment_0") as RecyclerViewFragment
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    val fragment_2 = Fragment_2()
    val tabLayoutViewPagerFragment = TabLayoutViewPagerFragment()

    val mOnNavigationItemSelectedListener = object : BottomNavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
            Log.e("==",menuItem.toString())
            when (menuItem.itemId) {
                R.id.main -> {
                    showFragment(recyclerViewFragment, "fragment_0")
                    hideFragment(tabLayoutViewPagerFragment, fragment_2)
                    return true
                }

                R.id.chat -> {
                    showFragment(tabLayoutViewPagerFragment, "fragment_1")
                    hideFragment(recyclerViewFragment, fragment_2)
                    return true
                }

                R.id.profile -> {
                    showFragment(fragment_2, "fragment_2")
                    hideFragment(recyclerViewFragment, tabLayoutViewPagerFragment)
                    return true
                }
            }
            return false
        }
    }

    fun showFragment(frag: Fragment, tag: String) {
        transaction = supportFragmentManager.beginTransaction()
        if (supportFragmentManager.findFragmentByTag(tag) == null) {
            transaction.add(R.id.forFragment, frag, tag)
        } else {
            transaction.show(frag)
        }
    }

    fun hideFragment(hfrag_1: Fragment, hfrag_2: Fragment) {
        if (supportFragmentManager.findFragmentByTag(hfrag_1.tag) != null) transaction.hide(hfrag_1)
        if (supportFragmentManager.findFragmentByTag(hfrag_2.tag) != null) transaction.hide(hfrag_2)

        transaction.commit()
    }

}
