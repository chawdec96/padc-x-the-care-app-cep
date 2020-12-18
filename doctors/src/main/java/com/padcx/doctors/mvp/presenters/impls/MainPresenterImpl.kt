package com.padcx.doctors.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padcx.doctors.mvp.presenters.MainPresenter
import com.padcx.doctors.mvp.views.MainView
import com.padcx.shared.data.models.TheCareAppModel
import com.padcx.shared.data.models.TheCareAppModelImpl
import com.padcx.shared.data.vos.ConsultationRequestVO
import com.padcx.shared.mvp.presenters.AbstractBasePresenter

class MainPresenterImpl: MainPresenter, AbstractBasePresenter<MainView>() {

    private val mTheCareAppModel: TheCareAppModel = TheCareAppModelImpl

    override fun onUIReady(owner: LifecycleOwner, doctorId: String, specialityId: String) {
        mTheCareAppModel.getConsultationRequestFromPatient(specialityId, onSuccess = {
            mView?.showAppointment(it)
        }, onFailure = {

        })

        mTheCareAppModel.getConsultationsForADoctorFromFirebaseAndSaveToDB(doctorId, onFailure = {
            mView?.showError(it)
        })

        mTheCareAppModel.getConsultations()
            .observe(owner, Observer {
                mView?.showConsultations(it)
            })

    }

    override fun onTapBtnAccept(consultationRequestVO: ConsultationRequestVO) {
        consultationRequestVO.isAccept = true
        mTheCareAppModel.addConsultationRequest(consultationRequestVO)
        mView?.showPatientInfo(consultationRequestVO)
    }

    override fun onTapBtnCancel(consultationRequestVO: ConsultationRequestVO) {
        consultationRequestVO.isAccept = false
        mTheCareAppModel.addConsultationRequest(consultationRequestVO)
    }

    override fun onTapBtnCallMeBack() {
        TODO("Not yet implemented")
    }

    override fun onTapBtnPostpone() {
        TODO("Not yet implemented")
    }

}