package com.padcx.doctors.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcx.doctors.mvp.views.PatientInfoView
import com.padcx.shared.data.vos.ConsultationRequestVO
import com.padcx.shared.mvp.presenters.BasePresenter

interface PatientInfoPresenter: BasePresenter<PatientInfoView> {

    fun onUIReady(owner: LifecycleOwner, consultationRequestVO: ConsultationRequestVO)

    fun onCreateConsultation(owner: LifecycleOwner, consultationRequestVO: ConsultationRequestVO)
    fun onUpdateConsultationRequest(owner: LifecycleOwner, consultationRequestVO: ConsultationRequestVO)
}