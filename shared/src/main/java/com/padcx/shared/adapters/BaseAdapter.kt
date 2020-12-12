package com.padcx.shared.adapters

import androidx.recyclerview.widget.RecyclerView
import com.padcx.shared.views.viewholders.BaseViewHolder

abstract class BaseAdapter<T: BaseViewHolder<W>, W>: RecyclerView.Adapter<T>() {

    protected var mData: ArrayList<W> = arrayListOf()

    override fun getItemCount(): Int {
        return mData.count()
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.bindData(mData[position])
    }

    fun setNewData(newData: List<W>){

        if (newData.isEmpty()){
            mData.clear()
        }else{
            mData = ArrayList(newData)
        }
        notifyDataSetChanged()
    }

    fun clearData(){
        mData = arrayListOf()
        notifyDataSetChanged()
    }
}