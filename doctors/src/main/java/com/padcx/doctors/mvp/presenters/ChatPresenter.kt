package com.padcx.doctors.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcx.shared.data.vos.*

interface ChatPresenter {

    fun onUIReady(owner: LifecycleOwner)
    fun onTapBtnSend(message: String, image: String)
    fun onTapEndConsultation(
        caseSummaryVO: CaseSummaryVO, doctorVO: DoctorVO,
        patientVO: PatientVO, prescriptions: List<PrescriptionVO>,
        type: String, chats: List<ChatVO>
    )
}