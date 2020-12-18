package com.padcx.doctors.mvp.views

import com.padcx.shared.data.vos.ConsultationRequestVO
import com.padcx.shared.data.vos.ConsultationVO
import com.padcx.shared.mvp.views.BaseView

interface MainView: BaseView {

    fun showAppointment(consultations: List<ConsultationRequestVO>)
    fun showConsultations(consultations: List<ConsultationVO>)
    fun showPatientInfo(consultationRequestVO: ConsultationRequestVO)

    fun navigateToMedicineDialog()
    fun navigateToMedicalHistoryDialog()
}