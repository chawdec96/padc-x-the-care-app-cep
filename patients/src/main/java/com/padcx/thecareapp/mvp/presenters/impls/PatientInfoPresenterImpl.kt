package com.padcx.thecareapp.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.padcx.shared.data.models.TheCareAppModel
import com.padcx.shared.data.models.TheCareAppModelImpl
import com.padcx.shared.data.vos.*
import com.padcx.shared.mvp.presenters.AbstractBasePresenter
import com.padcx.thecareapp.mvp.presenters.PatientInfoPresenter
import com.padcx.thecareapp.mvp.views.PatientInfoView
import com.padcx.thecareapp.persistence.PatientPrefs

class PatientInfoPresenterImpl: PatientInfoPresenter, AbstractBasePresenter<PatientInfoView>() {

    private val mTheCareAppModelImpl: TheCareAppModel = TheCareAppModelImpl

    override fun getGeneralQuestions(owner: LifecycleOwner, documentId: String) {
        mTheCareAppModelImpl.getGeneralQuestions(documentId, onSuccess = {
            mView?.showPatientInfo()
        }, onFailure = {
            mView?.showError(it)
        })
    }

    override fun onTapBtnContinue(
        owner: LifecycleOwner,
        specialityId: String,
        email: String,
        password: String,
        username: String,
        phone: String,
        image: String,
        dob: String,
        height: String,
        bloodType: String,
        weight: String,
        bloodPressure: String,
        allergicMedicine: String
    ) {
        mTheCareAppModelImpl.setPatient(
            email, password, username, phone, image,
            dob, height, bloodType, weight, bloodPressure, allergicMedicine
        )
        mTheCareAppModelImpl.getPatient(email, onSuccess = {
            PatientPrefs.setUserInfo(it)
        }, onFailure = {
            mView?.showError(it)
        })

        mTheCareAppModelImpl.getSpecificQuestions(specialityId, onSuccess = {
            it.apply {
                mView?.showSpecificQuestions(this)
            }
        }, onFailure = {
            mView?.showError(it)
        })
    }

    override fun onTapBtnConsultant(owner: LifecycleOwner, consultationRequestVO: ConsultationRequestVO) {
        mView?.showPatientInfoDialog(consultationRequestVO)
    }

    override fun onTapEditText(owner: LifecycleOwner) {
        mView?.showPatientInfo()
    }

    override fun onTapAnswerEditText(specificQuestionVO: SpecificQuestionVO, answer: String) {
//        mTheCareAppModelImpl.setSpecificQuestions(PatientPrefs.getUserId()!!, specificQuestionVO)
        val caseSummary = CaseSummaryVO()
        caseSummary.id = specificQuestionVO.id
        caseSummary.question = specificQuestionVO.sentence
        caseSummary.answer = answer
        caseSummary.type = specificQuestionVO.type

        mView?.addSpecificQuestionsWithAnswer(caseSummary)
    }

    override fun updateGeneralQuestions(owner: LifecycleOwner, documentId: String, questionVO: GeneralQuestionVO) {
        mTheCareAppModelImpl.setGeneralQuestions(documentId, questionVO)
    }
}