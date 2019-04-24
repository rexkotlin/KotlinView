package com.ex.kotlinview.bottomnavigation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.bottomappbar.BottomAppBar
import android.widget.Toast
import com.ex.kotlinview.R
import kotlinx.android.synthetic.main.activity_bottom_app_bar.*

class BottomAppBarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_app_bar)

        bottomAppBar.setNavigationOnClickListener {
            bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
        }
    }
}
