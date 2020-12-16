package com.padcx.thecareapp.mvp.views

import com.padcx.shared.data.vos.DoctorVO
import com.padcx.shared.data.vos.GeneralQuestionVO
import com.padcx.shared.data.vos.SpecificQuestionVO
import com.padcx.shared.mvp.views.BaseView

interface MainView: BaseView {

    fun addGeneralQuestionsToPatientVO(questions: List<GeneralQuestionVO>)
    fun addRecentlyDoctorsToPatientVO(doctors: List<DoctorVO>)
}