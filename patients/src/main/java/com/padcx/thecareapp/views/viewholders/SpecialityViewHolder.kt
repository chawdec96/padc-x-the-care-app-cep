package com.padcx.thecareapp.views.viewholders

import android.view.View
import com.padcx.shared.data.vos.SpecialityVO
import com.padcx.shared.views.viewholders.BaseViewHolder
import com.padcx.thecareapp.delegates.SpecialityItemDelegate
import kotlinx.android.synthetic.main.item_speciality.view.*

class SpecialityViewHolder(itemView: View, private val delegate: SpecialityItemDelegate) : BaseViewHolder<SpecialityVO>(itemView) {

    override fun bindData(data: SpecialityVO) {
        itemView.tvSpeciality.text = data.name

        itemView.setOnClickListener { delegate.onTapItem(data)}
    }
}