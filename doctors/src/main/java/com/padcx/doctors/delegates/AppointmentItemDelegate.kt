package com.padcx.doctors.delegates

import com.padcx.shared.data.vos.ConsultationRequestVO

interface AppointmentItemDelegate {

    fun onTapBtnAccept(consultationRequestVO: ConsultationRequestVO)
    fun onTapBtnCancel(consultationRequestVO: ConsultationRequestVO)
    fun onTapBtnCallMeBack()
    fun onTapBtnPostpone()

}