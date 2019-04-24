package com.ex.kotlinview

import android.app.Activity
import android.support.v7.widget.RecyclerView;
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andresjakl.partslist.PartData
import kotlinx.android.synthetic.main.part_list_item.view.*

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.*

class PartAdapter(val activity: Activity, val clickListener: (PartData) -> Unit) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val ITEM_TYPE_NORMAL = 1
    private val ITEM_TYPE_LOADING = 0
    private var hasNext: Boolean = false

    private val dto = ArrayList<PartData>()

    var page = 0

    private val loadMoreCallback = PublishSubject.create<String>()

    fun getLoadMoreCallback(): Observable<String> {
        return loadMoreCallback
    }

    fun updateData(dto: ArrayList<PartData>, hasNext: Boolean, page: Int) {
        this.dto.clear()
        this.page = page
        this.dto.addAll(dto)
        this.hasNext = hasNext
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // LayoutInflater: takes ID from layout defined in XML.
        // Instantiates the layout XML into corresponding View objects.
        // Use context from main app -> also supplies theme layout values!
        val inflater = LayoutInflater.from(parent.context)

        return if (viewType == ITEM_TYPE_LOADING) {
            val view = inflater.inflate(R.layout.item_loading, parent, false)
            LoadingViewHolder(view)
        } else {
            val view = inflater.inflate(R.layout.part_list_item, parent, false)
            PartViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // Populate ViewHolder with data that corresponds to the position in the list
        // which we are told to load
//        (holder as PartViewHolder).bind(partItemList[position], clickListener)

        if (holder is PartViewHolder) {
            Log.d("textlog", "position:" + position)
            (holder as PartViewHolder).bind(dto[position], clickListener)
        } else if (holder is LoadingViewHolder) {
            Log.d("textlog", "loadMore:" + position)
            (holder as LoadingViewHolder).loadMore()
        }
    }

    override fun getItemViewType(position: Int): Int {
//        return if (position ==partItemList.size && hasNext) {
//        Log.d("getItemViewType", "dto:" +(dto.size -1))
        Log.d("getItemViewType", "getItemViewType:" +position)
        return if (position == dto.size  && hasNext) {
//            Log.d("getItemViewType", "getItemViewType:" +position)
//            hasNext = true
            ITEM_TYPE_LOADING
        } else {
//            hasNext = false
            ITEM_TYPE_NORMAL
        }
    }

    //    override fun getItemCount() = partItemList.size
    override fun getItemCount(): Int {
        Log.d("textlog", "dto.size:" + dto.size);
        if (dto.size == null) {
            return 0
        } else if ( dto.size == this.page) {
            Log.d("textlog", "partItemList.size:+1:"+dto.size)
            hasNext = true
            return dto.size + 1
        }  else {
            return dto.size
        }
    }


    inner class PartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(part: PartData, clickListener: (PartData) -> Unit) {
            itemView.tv_part_item_name.text = part.itemName
            Log.d("part.id.toString()", "partItemList.size:+1:"+part.id.toString())
            itemView.tv_part_id.text = part.id.toString()
            itemView.setOnClickListener { clickListener(part) }
        }
    }

    internal inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun loadMore() {
            Timer().schedule(object : TimerTask() {
                override fun run() {
                    activity.runOnUiThread {
                        loadMoreCallback.onNext("")
                    }
                }
            }, 1000)
        }
    }

}