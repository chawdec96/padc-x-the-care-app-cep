package com.padcx.doctors.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.padcx.doctors.mvp.presenters.ChatPresenter
import com.padcx.doctors.mvp.views.ChatView
import com.padcx.shared.data.models.TheCareAppModel
import com.padcx.shared.data.models.TheCareAppModelImpl
import com.padcx.shared.data.vos.*
import com.padcx.shared.mvp.presenters.AbstractBasePresenter

class ChatPresenterImpl: ChatPresenter, AbstractBasePresenter<ChatView>() {

    private val mTheCareAppModelImpl: TheCareAppModel = TheCareAppModelImpl

    override fun onUIReady(owner: LifecycleOwner) {
        TODO("Not yet implemented")
    }

    override fun onTapBtnSend(message: String, image: String) {
        TODO("Not yet implemented")
    }

    override fun onTapEndConsultation(
        caseSummaryVO: CaseSummaryVO, doctorVO: DoctorVO,
        patientVO: PatientVO, prescriptions: List<PrescriptionVO>,
        type: String, chats: List<ChatVO>
    ) {
//        mTheCareAppModelImpl.addConsultationByDoctor(
//            caseSummaryVO, doctorVO,patientVO, prescriptions, type, chats
//        )
    }
}