package com.padcx.shared.data.models

import androidx.lifecycle.LiveData
import com.padcx.shared.data.vos.*
import com.padcx.shared.network.FirebaseApi

interface TheCareAppModel {

    var mFirebaseApi: FirebaseApi

    fun getSpecialitiesFromFirebaseAndSaveToDB(onFailure: (String) -> Unit)
    fun getSpecialities(): LiveData<List<SpecialityVO>>

    fun setDoctor(doctorVO: DoctorVO)
    fun getDoctors(onSuccess: (doctors: List<DoctorVO>) -> Unit, onFailure: (String) -> Unit)

    fun setPatient(email: String, password: String, username: String, phone: String, image: String)
    fun getPatients(onSuccess: (patients: List<PatientVO>) -> Unit, onFailure: (String) -> Unit)

    fun getQuestionsTemplateFromFirebaseAndSaveToDB(onFailure: (String) -> Unit)
    fun getQuestionsTemplate(): LiveData<List<QuestionTemplateVO>>

    fun setChatMessage(consultationId: String, chatVO: ChatVO)
    fun getChatMessage(
        consultationId: String,
        onSuccess: (chats: List<ChatVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun addConsultationRequestByPatient(
        caseSummaryVO: CaseSummaryVO,
        patients: PatientVO,
        type: String
    )

    fun getConsultationRequestFromPatient(
        onSuccess: (consultationRequest: List<ConsultationRequestVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun addConsultationByDoctor(
        caseSummaryVO: CaseSummaryVO, doctorVO: DoctorVO,
        patients: PatientVO, prescriptionVO: List<PrescriptionVO>,
        type: String, chat: List<ChatVO>
    )

    fun setRecentlyDoctors(patientId: String, doctorVO: DoctorVO)
    fun getRecentlyDoctors(
        patientId: String,
        onSuccess: (doctors: List<DoctorVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getConsultationsForAPatientFromFirebaseAndSaveToDB(
        patientId: String,
        onFailure: (String) -> Unit
    )

    fun getConsultationsForADoctorFromFirebaseAndSaveToDB(
        doctorId: String,
        onFailure: (String) -> Unit
    )

    fun getConsultations(): LiveData<List<ConsultationVO>>

    fun addCheckOutByPatient(
        deliveryRoutineVO: DeliveryRoutineVO,
        doctorVO: DoctorVO, patients: PatientVO, createdAt: String,
        sendAddress: String, specialityId: String
    )

    fun getCheckOutsFromFirebaseAndSaveToDB(patientId: String, onFailure: (String) -> Unit)
    fun getCheckOuts(): LiveData<List<CheckOutVO>>

    fun setPrescriptionMedicine(
        nodeName: String,
        documentId: String,
        prescriptionVO: PrescriptionVO
    )

    fun getPrescriptionMedicines(
        nodeName: String, documentId: String,
        onSuccess: (prescriptions: List<PrescriptionVO>) -> Unit, onFailure: (String) -> Unit
    )


}