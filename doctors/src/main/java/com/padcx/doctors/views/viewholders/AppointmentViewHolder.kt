package com.padcx.doctors.views.viewholders

import android.view.View
import com.padcx.doctors.delegates.AppointmentItemDelegate
import com.padcx.shared.data.vos.ConsultationRequestVO
import com.padcx.shared.views.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.viewpod_appointment_request.view.*

class AppointmentViewHolder(itemView: View, private val delegate: AppointmentItemDelegate) : BaseViewHolder<ConsultationRequestVO>(itemView) {
    override fun bindData(data: ConsultationRequestVO) {

        itemView.tvPatientName.text = data.patient.username
        itemView.tvPatientDob.text = "မွေးနေ့ ${data.patient.dob}"

        itemView.btnCancel.setOnClickListener {
            delegate.onTapBtnCancel(data)
        }

        itemView.btnAccept.setOnClickListener {
            delegate.onTapBtnAccept(data)
        }

    }
}