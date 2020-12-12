package com.padcx.thecareapp.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.padcx.shared.data.vos.PatientVO
import com.padcx.shared.mvp.presenters.AbstractBasePresenter
import com.padcx.thecareapp.mvp.presenters.PatientInfoPresenter
import com.padcx.thecareapp.mvp.views.PatientInfoView

class PatientInfoPresenterImpl: PatientInfoPresenter, AbstractBasePresenter<PatientInfoView>() {

    override fun getPatientInfo(owner: LifecycleOwner) {
    }

    override fun onTapBtnContinue(owner: LifecycleOwner, patientVO: PatientVO) {
        mView?.showPatientInfo(patientVO)
    }

    override fun onTapBtnConsultant(owner: LifecycleOwner, patientVO: PatientVO) {
        mView?.navigateToChatScreen()
    }
}