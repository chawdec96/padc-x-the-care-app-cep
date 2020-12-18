package com.padcx.doctors.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.padcx.doctors.DoctorPrefs
import com.padcx.doctors.mvp.presenters.PatientInfoPresenter
import com.padcx.doctors.mvp.views.PatientInfoView
import com.padcx.shared.data.models.TheCareAppModel
import com.padcx.shared.data.models.TheCareAppModelImpl
import com.padcx.shared.data.vos.ConsultationRequestVO
import com.padcx.shared.mvp.presenters.AbstractBasePresenter

class PatientInfoPresenterImpl: PatientInfoPresenter, AbstractBasePresenter<PatientInfoView>() {

    private val mTheCareAppModel: TheCareAppModel = TheCareAppModelImpl

    override fun onUIReady(owner: LifecycleOwner, consultationRequestVO: ConsultationRequestVO) {
        mTheCareAppModel.getCaseSummarySubCollection(consultationRequestVO.id!!, onSuccess = {
            consultationRequestVO.caseSummary.addAll(it)
            mView?.showPatientInfo(consultationRequestVO)
        }, onFailure = {
            mView?.showError(it)
        })
    }

    override fun onCreateConsultation(owner: LifecycleOwner, consultationRequestVO: ConsultationRequestVO) {
        mTheCareAppModel.addConsultationByDoctor(
            DoctorPrefs.getUserInfo(), consultationRequestVO.patient,
            consultationRequestVO.specialityId!!, true)

        mTheCareAppModel.addCaseSummarySubCollectionToConsultation(
            "CON${DoctorPrefs.getUserInfo().phone}",
            consultationRequestVO.caseSummary
        )

    }

    override fun onUpdateConsultationRequest(
        owner: LifecycleOwner,
        consultationRequestVO: ConsultationRequestVO
    ) {
        consultationRequestVO.isAccept = true
        mTheCareAppModel.addConsultationRequest(consultationRequestVO)
    }
}