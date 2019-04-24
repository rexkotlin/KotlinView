package com.ex.kotlinview.bottomnavigation

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ex.kotlinview.R
import kotlinx.android.synthetic.main.activity_other.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class Fragment_2 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_other, container, false)
//        view.imageView.setImageResource(R.drawable.ic_tag_faces_black_24dp)

        view.other_button.setOnClickListener {
            val intent = Intent(activity, BottomAppBarActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}
