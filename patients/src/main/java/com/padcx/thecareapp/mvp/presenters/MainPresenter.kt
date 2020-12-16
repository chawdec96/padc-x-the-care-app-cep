package com.padcx.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcx.shared.data.vos.*
import com.padcx.shared.mvp.presenters.BasePresenter
import com.padcx.thecareapp.mvp.views.MainView

interface MainPresenter : BasePresenter<MainView> {

    fun onUIReady(owner: LifecycleOwner, patientId: String)
//    fun getGeneralQuestions(owner: LifecycleOwner, patientId: String)
//    fun getRecentlyDoctors(owner: LifecycleOwner, patientId: String)

    //Test Firebase
//    fun broadcastConsultationRequest(caseSummaryVO: CaseSummaryVO, patientVO: PatientVO, specialityId: String)
//    fun chatMessage(consultationId: String, chatVO: ChatVO)
//    fun setCheckOut(
//        prescriptionList: List<PrescriptionVO>, deliveryRoutineVO: DeliveryRoutineVO,
//        doctorVO: DoctorVO, patientVO: PatientVO, createdAt: String,
//        sendAddress: String, specialityId: String
//    )
//
//    fun getChatMessage(consultationId: String)
//    fun getCheckOut(owner: LifecycleOwner,patientId: String)
//    fun getConsultations(owner: LifecycleOwner,patientId: String)
//    fun getRecentlyDoctors(patientId: String)
//
//    fun registerPatient(email: String, password: String, username: String, phone: String, image: String)

}