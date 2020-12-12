package com.padcx.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcx.shared.data.vos.PatientVO
import com.padcx.shared.mvp.presenters.BasePresenter
import com.padcx.thecareapp.mvp.views.PatientInfoView

interface PatientInfoPresenter: BasePresenter<PatientInfoView> {

    fun getPatientInfo(owner: LifecycleOwner)
    fun onTapBtnContinue(owner: LifecycleOwner, patientVO: PatientVO)
    fun onTapBtnConsultant(owner: LifecycleOwner, patientVO: PatientVO)

}