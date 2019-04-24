package com.ex.kotlinview.bottomnavigation

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.andresjakl.partslist.PartData
import com.ex.kotlinview.PartAdapter
import com.ex.kotlinview.PartDetailActivity
import com.ex.kotlinview.R
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.main_recyclerview_fragment.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class RecyclerViewFragment : Fragment() {
//    val intent = intent

    val partList = ArrayList<PartData>()
    val disposable = CompositeDisposable()

    private var partAdapter: PartAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View? = inflater.inflate(R.layout.main_recyclerview_fragment, container, false)
//        view!!.frag_button.setOnClickListener { intent.intent(this.context, Activity_2()) }
        view?.recyclerView?.layoutManager = LinearLayoutManager(activity)


        createTestData()

//        partAdapter?.let {
//            PartAdapter(activity!!) { partItem: PartData -> partItemClicked(partItem) }
//        }

        activity?.let {
            partAdapter = PartAdapter(it) { partItem: PartData -> partItemClicked(partItem) }
        }

        partAdapter?.updateData(partList, false, partList.size)

        view?.recyclerView?.adapter = partAdapter
//        rv_parts.adapter = partAdapter(testData, { partItem : PartData -> partItemClicked(partItem) })
        disposable.add((view?.recyclerView?.adapter as PartAdapter).getLoadMoreCallback().subscribe
        { retrievedNews ->
            //            Log.d("textlog", "loadMore$retrievedNews")
            createTestData()
            partAdapter?.updateData(partList, false, partList.size)
//            recyclerView.adapter = partAdapter
//          partAdapter!!.notifyDataSetChanged()
        })


        return view
    }

    private fun partItemClicked(partItem: PartData) {
        Toast.makeText(activity, "Clicked: ${partItem.itemName}", Toast.LENGTH_LONG).show()

        // Launch second activity, pass part ID as string parameter
        val showDetailActivityIntent = Intent(activity, PartDetailActivity::class.java)
        showDetailActivityIntent.putExtra(Intent.EXTRA_TEXT, partItem.id.toString())
        startActivity(showDetailActivityIntent)
    }

    //    var index = 0
    private fun createTestData() {
//        var partList = ArrayList<PartData>()
//        partList.clear()
        for (index in 0..9) {
//            this.index = index + 1
//            partList.add(index,PartData(this.index , "LED Green 568 nm, 5mm"))
            partList.add(PartData(index + 1, "LED Green 568 nm, 5mm"))
        }
//        partList.add(PartData(100411, "LED Green 568 nm, 5mm"))
//        partList.add(PartData(101119, "Aluminium Capacitor 4.7μF"))
//        partList.add(PartData(101624, "Potentiometer 500kΩ"))
//        partList.add(PartData(100411, "LED Green 568 nm, 5mm"))
//        partList.add(PartData(101119, "Aluminium Capacitor 4.7μF"))
//        partList.add(PartData(101624, "Potentiometer 500kΩ"))
//        partList.add(PartData(100411, "LED Green 568 nm, 5mm"))
//        partList.add(PartData(101119, "Aluminium Capacitor 4.7μF"))
//        partList.add(PartData(101624, "Potentiometer 500kΩ"))
//        partList.add(PartData(100411, "LED Green 568 nm, 5mm"))
    }

    override fun onPause() {

        super.onPause()
    }

    override fun onStop() {

        super.onStop()
    }

    override fun onStart() {

        super.onStart()

    }

    override fun onResume() {

        super.onResume()
    }

}
