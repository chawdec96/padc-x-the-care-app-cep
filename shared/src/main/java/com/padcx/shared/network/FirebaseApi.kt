package com.padcx.shared.network

import com.padcx.shared.data.vos.*

interface FirebaseApi {

    fun registeredPatient(
        email: String, password: String, username: String, phone: String, image: String
    )

    fun registeredDoctor(
        email: String, password: String,
        username: String, phone: String,
        image: String, speciality: String,
        experience: Int, doctorSignature: String,
        degree: String, university: String
    )

    fun addedConsultationRequestByPatient(
        caseSummaryVO: CaseSummaryVO, patientVO: PatientVO, type: String
    )

    fun addedConsultationByDoctor(
        caseSummaryVO: CaseSummaryVO, doctors: DoctorVO,
        patientVO: PatientVO, prescriptionVO: PrescriptionVO,
        type: String, chat: List<ChatVO>
    )

    fun addedCheckOutByPatient(
        prescriptionList: List<PrescriptionVO>, deliveryRoutine: DeliveryRoutineVO,
        doctorVO: DoctorVO, patientVO: PatientVO, createdAt: String,
        sendAddress: String, specialityId: String
    )

    fun getDoctors(onSuccess: (doctors: List<DoctorVO>) -> Unit, onFailure: (String) -> Unit)

    fun getPatients(onSuccess: (patients: List<PatientVO>) -> Unit, onFailure: (String) -> Unit)

    fun getSpecialities(onSuccess: (specialities: List<SpecialityVO>) -> Unit, onFailure: (String) -> Unit)

    fun getConsultationRequestFromPatient(
        onSuccess: (consultation: List<ConsultationRequestVO>) -> Unit, onFailure: (String) -> Unit
    )

    fun getConsultationsForAPatient(
        patientId: String, onSuccess: (consultations: List<ConsultationVO>) -> Unit, onFailure: (String) -> Unit
    )

    fun getQuestionTemplateForDoctor(
        onSuccess: (doctors: List<DoctorVO>) -> Unit, onFailure: (String) -> Unit
    )


}