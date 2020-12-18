package com.padcx.shared.data.models

import androidx.lifecycle.LiveData
import com.padcx.shared.data.vos.*
import com.padcx.shared.network.FirebaseApi

interface TheCareAppModel {

    var mFirebaseApi: FirebaseApi

    fun getSpecialitiesFromFirebaseAndSaveToDB(onFailure: (String) -> Unit)
    fun getSpecialities(): LiveData<List<SpecialityVO>>

    fun getSpecificQuestions(
        specialityId: String,
        onSuccess: (specificQuestions: List<SpecificQuestionVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun setDoctor(doctorVO: DoctorVO)
    fun getDoctors(onSuccess: (doctors: List<DoctorVO>) -> Unit, onFailure: (String) -> Unit)
    fun getDoctor(email: String, onSuccess: (doctor: DoctorVO) -> Unit, onFailure: (String) -> Unit)

    fun setPatient(
        email: String, password: String, username: String, phone: String, image: String,
        dob: String, height: String, bloodType: String, weight: String, bloodPressure: String,
        allergicMedicine: String
    )
    fun getPatients(onSuccess: (patients: List<PatientVO>) -> Unit, onFailure: (String) -> Unit)
    fun getPatient(email: String, onSuccess: (patients: PatientVO) -> Unit, onFailure: (String) -> Unit)

    fun setSpecificQuestions(patientId: String, questions: SpecificQuestionVO)
    fun getSpecificQuestionsForPatient(patientId: String, onSuccess: (specificQuestions: List<SpecificQuestionVO>) -> Unit, onFailure: (String) -> Unit)

    fun setGeneralQuestions(documentId: String, questions: GeneralQuestionVO)
    fun getGeneralQuestions(documentId: String, onSuccess: (questions: List<GeneralQuestionVO>) -> Unit, onFailure: (String) -> Unit)

    fun getQuestionsTemplateFromFirebaseAndSaveToDB(onFailure: (String) -> Unit)
    fun getQuestionsTemplate(): LiveData<List<QuestionTemplateVO>>

    fun setChatMessage(consultationId: String, chatVO: ChatVO)
    fun getChatMessage(
        consultationId: String,
        onSuccess: (chats: List<ChatVO>) -> Unit,
        onFailure: (String) -> Unit
    )

//    fun addConsultationRequestByPatient(
//        caseSummaryVO: CaseSummaryVO,
//        patients: PatientVO,
//        type: String
//    )

    fun addConsultationRequest(consultationRequestVO: ConsultationRequestVO)

    fun addCaseSummarySubCollectionToConsultation(documentId: String, caseSummary: List<CaseSummaryVO>)

    fun getCaseSummarySubCollection(
        documentId: String,
        onSuccess: (caseSummary: List<CaseSummaryVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getConsultationRequestFromPatient(
        specialityId: String,
        onSuccess: (consultationRequest: List<ConsultationRequestVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun addConsultationByDoctor(doctorVO: DoctorVO, patients: PatientVO, type: String, isStart: Boolean)

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