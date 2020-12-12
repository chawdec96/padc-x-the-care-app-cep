package com.padcx.thecareapp.mvp.views

import com.padcx.shared.data.vos.PatientVO
import com.padcx.shared.data.vos.SpecificQuestionVO
import com.padcx.shared.mvp.views.BaseView

interface PatientInfoView: BaseView {

    fun showPatientInfo(patientVO: PatientVO)
    fun showSpecificQuestions(SpecificQuestions: List<SpecificQuestionVO>)
    fun navigateToChatScreen()

}