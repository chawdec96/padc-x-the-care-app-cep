package com.padcx.thecareapp.mvp.views

import com.padcx.shared.data.vos.CaseSummaryVO
import com.padcx.shared.data.vos.ConsultationRequestVO
import com.padcx.shared.data.vos.PatientVO
import com.padcx.shared.data.vos.SpecificQuestionVO
import com.padcx.shared.mvp.views.BaseView

interface PatientInfoView: BaseView {

    fun showPatientInfo()
    fun showSpecificQuestions(specificQuestions: List<SpecificQuestionVO>)
    fun addSpecificQuestionsWithAnswer(caseSummaryVO: CaseSummaryVO)
    fun showPatientInfoDialog(consultationRequestVO: ConsultationRequestVO)

}