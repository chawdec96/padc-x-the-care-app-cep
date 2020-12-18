package com.padcx.doctors.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.doctors.R
import com.padcx.doctors.delegates.AppointmentItemDelegate
import com.padcx.doctors.views.viewholders.AppointmentViewHolder
import com.padcx.shared.adapters.BaseAdapter
import com.padcx.shared.data.vos.ConsultationRequestVO

class AppointmentRecyclerAdapter(private val delegate: AppointmentItemDelegate):
    BaseAdapter<AppointmentViewHolder, ConsultationRequestVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        return AppointmentViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.viewpod_appointment_request, parent, false),
            delegate
        )
    }
}