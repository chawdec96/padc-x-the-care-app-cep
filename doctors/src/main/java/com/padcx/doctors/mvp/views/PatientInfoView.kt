package com.padcx.doctors.mvp.views

import com.padcx.shared.data.vos.ConsultationRequestVO
import com.padcx.shared.mvp.views.BaseView

interface PatientInfoView: BaseView {

    fun showPatientInfo(consultationRequestVO: ConsultationRequestVO)
}