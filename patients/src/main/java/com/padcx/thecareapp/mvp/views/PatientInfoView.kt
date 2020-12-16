package com.padcx.thecareapp.mvp.views

import com.padcx.shared.data.vos.PatientVO
import com.padcx.shared.data.vos.SpecificQuestionVO
import com.padcx.shared.mvp.views.BaseView

interface PatientInfoView: BaseView {

    fun showPatientInfo()
    fun showSpecificQuestions(specificQuestions: List<SpecificQuestionVO>)
    fun addSpecificQuestionsWithAnswer(specificQuestionVO: SpecificQuestionVO)
    fun showPatientInfoDialog(patientVO: PatientVO)

}