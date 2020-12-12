package com.padcx.shared.network

import com.padcx.shared.data.vos.*

interface FirebaseApi {

    fun registeredPatient(
        email: String, password: String, username: String, phone: String, image: String
    )
//    fun setPatient(patientVO: PatientVO)
//
//    fun setDoctor(doctors: DoctorVO)

    fun registeredDoctor(
        email: String, password: String,
        username: String, phone: String,
        image: String, speciality: String,
        experience: Int, doctorSignature: String,
        degree: String, university: String
    )

    fun setConsultationRequestByPatient(
        caseSummaryVO: CaseSummaryVO, patientVO: PatientVO, type: String
    )

    fun setConsultationByDoctor(
        caseSummaryVO: CaseSummaryVO, doctors: DoctorVO,
        patientVO: PatientVO, prescriptionVO: List<PrescriptionVO>,
        type: String, chat: List<ChatVO>
    )

    fun setCheckOutByPatient(
        deliveryRoutine: DeliveryRoutineVO,
        doctorVO: DoctorVO, patientVO: PatientVO, createdAt: String,
        sendAddress: String, specialityId: String
    )

    fun setRecentDoctorSubCollectionForAPatient(patientId: String,doctors: DoctorVO)

    fun setGeneralQuestionSubCollectionForAPatine(patientId: String, questionTemplateVO: QuestionTemplateVO)

    fun setPrescriptionSubCollection(nodeName: String, documentId: String, prescriptionVO: PrescriptionVO)

//    fun setPrescriptionSubCollectionToConsultation(consultationId: String, prescriptionVO: PrescriptionVO)

    fun setChatSubCollectionToConsultation(consultationId: String, chatVO: ChatVO)

    fun getDoctors(onSuccess: (doctors: List<DoctorVO>) -> Unit, onFailure: (String) -> Unit)

    fun getPatients(onSuccess: (patients: List<PatientVO>) -> Unit, onFailure: (String) -> Unit)

    fun getSpecialities(
        onSuccess: (specialities: List<SpecialityVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getConsultationRequestFromPatient(
        onSuccess: (consultation: List<ConsultationRequestVO>) -> Unit, onFailure: (String) -> Unit
    )

    fun getConsultationsForAPatient(
        patientId: String,
        onSuccess: (consultations: List<ConsultationVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getConsultationsForADoctor(
        doctorId: String,
        onSuccess: (consultations: List<ConsultationVO>) -> Unit,
        onFailure: (String) -> Unit
    )


    fun getQuestionTemplateForDoctor(
        onSuccess: (questions: List<QuestionTemplateVO>) -> Unit, onFailure: (String) -> Unit
    )

    fun getCheckOutForAPatient(
        patientId: String,
        onSuccess: (checkOut: List<CheckOutVO>) -> Unit, onFailure: (String) -> Unit
    )

    fun getPrescriptions(
        noteName: String, documentId: String,
        onSuccess: (prescriptions: List<PrescriptionVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getChats(
        documentId: String,
        onSuccess: (chats: List<ChatVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMedicines(
        documentId: String,
        onSuccess: (medicines: List<MedicineVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getSpecificQuestions(
        documentId: String,
        onSuccess: (specificQuestions: List<QuestionTemplateVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getRecentDoctorsForAPatient(patientId: String, onSuccess: (doctors: List<DoctorVO>) -> Unit, onFailure: (String) -> Unit)

    fun getGeneralQuestionsForAPatient(patientId: String, onSuccess: (questions: List<QuestionTemplateVO>) -> Unit, onFailure: (String) -> Unit)
}