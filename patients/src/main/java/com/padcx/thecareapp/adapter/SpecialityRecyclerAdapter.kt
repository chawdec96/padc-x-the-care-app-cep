package com.padcx.thecareapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.shared.adapters.BaseAdapter
import com.padcx.shared.data.vos.SpecialityVO
import com.padcx.thecareapp.R
import com.padcx.thecareapp.delegates.SpecialityItemDelegate
import com.padcx.thecareapp.views.viewholders.SpecialityViewHolder

class SpecialityRecyclerAdapter(private val delegate: SpecialityItemDelegate): BaseAdapter<SpecialityViewHolder, SpecialityVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialityViewHolder {
        return SpecialityViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_speciality, parent, false),
            delegate
        )
    }
}