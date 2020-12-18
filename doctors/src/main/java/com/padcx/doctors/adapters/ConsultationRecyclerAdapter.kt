package com.padcx.doctors.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.doctors.R
import com.padcx.doctors.views.viewholders.ConsultationViewHolder
import com.padcx.shared.adapters.BaseAdapter
import com.padcx.shared.data.vos.ConsultationVO

class ConsultationRecyclerAdapter: BaseAdapter<ConsultationViewHolder, ConsultationVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsultationViewHolder {
        return ConsultationViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_appointment, parent, false)
        )
    }
}