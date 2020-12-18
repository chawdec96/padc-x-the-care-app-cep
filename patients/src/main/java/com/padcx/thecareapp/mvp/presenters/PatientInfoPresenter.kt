package com.padcx.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcx.shared.data.vos.CaseSummaryVO
import com.padcx.shared.data.vos.ConsultationRequestVO
import com.padcx.shared.data.vos.GeneralQuestionVO
import com.padcx.shared.data.vos.PatientVO
import com.padcx.shared.mvp.presenters.BasePresenter
import com.padcx.thecareapp.delegates.SpecificQuestionItemDelegate
import com.padcx.thecareapp.mvp.views.PatientInfoView

interface PatientInfoPresenter: BasePresenter<PatientInfoView>, SpecificQuestionItemDelegate {

    fun onTapBtnContinue(
        owner: LifecycleOwner, specialityId: String, email: String, password: String, username: String, phone: String,
        image: String, dob: String, height: String, bloodType: String, weight: String,
        bloodPressure: String, allergicMedicine: String
    )
    fun onTapBtnConsultant(owner: LifecycleOwner, consultationRequestVO: ConsultationRequestVO)
    fun onTapEditText(owner: LifecycleOwner)
    fun updateGeneralQuestions(owner: LifecycleOwner, documentId: String, questionVO: GeneralQuestionVO)
    fun getGeneralQuestions(owner: LifecycleOwner, documentId: String)

}