package com.padcx.shared.network.impls

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.padcx.shared.data.vos.*
import com.padcx.shared.network.FirebaseApi

object FirebaseCloudFirestoreApiImpl: FirebaseApi {

    private val db = Firebase.firestore

    override fun registeredPatient(
        email: String,
        password: String,
        username: String,
        phone: String,
        image: String
    ) {
        val patientMap = hashMapOf(
            "name" to username,
            "email" to email,
            "phone" to phone,
            "image" to image
        )

        db.collection("patients")
            .add(patientMap)
            .addOnSuccessListener { documentReference ->
                val id = documentReference.id
                db.collection("patients").document(id).update("pId", id)
                Log.d("Success", "Successfully added a patient") }
            .addOnFailureListener { Log.d("Failure", "Failed to add a patient") }

    }

    override fun registeredDoctor(
        email: String,
        password: String,
        username: String,
        phone: String,
        image: String,
        speciality: String,
        experience: Int,
        doctorSignature: String,
        degree: String,
        university: String
    ) {
        val doctorMap = hashMapOf(
            "name" to username,
            "email" to email,
            "phone" to phone,
            "image" to image,
            "speciality" to speciality,
            "experience" to experience,
            "doctorSignature" to doctorSignature,
            "degree" to degree,
            "university" to university
        )

        db.collection("doctors")
            .add(doctorMap)
            .addOnSuccessListener { documentReference ->
                val id = documentReference.id
                db.collection("doctors").document(id).update("dId", id)
                Log.d("Success", "Successfully added a doctor") }
            .addOnFailureListener { Log.d("Failure", "Failed to add a doctor") }
    }

    override fun addedConsultationRequestByPatient(
        caseSummaryVO: CaseSummaryVO,
        patientVO: PatientVO,
        type: String
    ) {
        val consultationMap = hashMapOf(
            "case_summary" to caseSummaryVO,
            "patient" to patientVO,
            "type" to type
        )

        db.collection("consultation_request")
            .add(consultationMap)
            .addOnSuccessListener { documentReference ->
                val id = documentReference.id
                db.collection("consultation_request").document(id).update("consReqId", id)
                Log.d("Success", "Successfully added a Request") }
            .addOnFailureListener { Log.d("Failure", "Failed to add a Request") }
    }

    override fun addedConsultationByDoctor(
        caseSummaryVO: CaseSummaryVO,
        doctorVO: DoctorVO,
        patientVO: PatientVO,
        prescriptionVO: PrescriptionVO,
        type: String,
        chat: List<ChatVO>
    ) {
        val consultationMap = hashMapOf(
            "case_summary" to caseSummaryVO,
            "doctor" to doctorVO,
            "patient" to patientVO,
            "prescription" to prescriptionVO,
            "type" to type,
            "chat" to chat
        )

        db.collection("consultations")
            .add(consultationMap)
            .addOnSuccessListener { documentReference ->
                val id = documentReference.id
                db.collection("consultations").document(id).update("consId", id)
                Log.d("Success", "Successfully added a Consultation") }
            .addOnFailureListener { Log.d("Failure", "Failed to add a Consultation") }

    }

    override fun addedCheckOutByPatient(
        prescriptionList: List<PrescriptionVO>,
        deliveryRoutine: DeliveryRoutineVO,
        doctorVO: DoctorVO,
        patientVO: PatientVO,
        createdAt: String,
        sendAddress: String,
        specialityId: String
    ) {
        val checkOutMap = hashMapOf(
            "prescription" to prescriptionList,
            "created_date" to createdAt,
            "delivery_routine" to deliveryRoutine,
            "doctor" to doctorVO,
            "patient" to patientVO,
            "delivery_address" to sendAddress,
            "specialityId" to specialityId
        )

        db.collection("check_out")
            .add(checkOutMap)
            .addOnSuccessListener { documentReference ->
                val id = documentReference.id
                db.collection("check_out").document(id).update("chOutId", id)
                Log.d("Success", "Successfully added a CheckOut") }
            .addOnFailureListener { Log.d("Failure", "Failed to add a CheckOut") }
    }

    override fun getDoctors(
        onSuccess: (doctors: List<DoctorVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("doctors")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it?.localizedMessage ?: "Please Check Internet")
                } ?: run {
                    val doctorList: MutableList<DoctorVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()
                    for (document in result){
                        val doctor = DoctorVO()
                        val data = document.data

                        doctor.id = document.id
                        doctor.name = data?.get("name") as String
                        doctor.email = data["email"] as String
                        doctor.phone = data["phone"] as String
                        doctor.password = data["password"] as String
                        doctor.image = data["image"] as String
                        doctor.degree = data["degree"] as String
                        doctor.doctorSignature = data["doctorSignature"] as String
                        doctor.speciality = data["speciality"] as String
                        doctor.university = data["university"] as String
                        doctor.experience = data["experience"] as String

                        doctorList.add(doctor)
                    }

                    onSuccess(doctorList)
                }
            }
    }

    override fun getPatients(
        onSuccess: (patients: List<PatientVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("patients")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it?.localizedMessage ?: "Please Check Internet")
                } ?: run {
                    val patientList: MutableList<PatientVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()

                    for (document in result){
                        val data = document.data

                        val patient = PatientVO()

                        patient.id = document.id
                        patient.username = data?.get("name") as String
                        patient.email = data["email"] as String
                        patient.image = data["image"] as String
                        patient.password = data["password"] as String
                        patient.phone = data["phone"] as String

                        patientList.add(patient)
                    }

                    onSuccess(patientList)
                }
            }
    }

    override fun getSpecialities(
        onSuccess: (specialities: List<SpecialityVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("specialities")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it?.localizedMessage ?: "Please Check Internet")
                } ?: run {
                    val specialityList: MutableList<SpecialityVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()

                    for (document in result){
                        val data = document.data

                        val speciality = SpecialityVO()

                        speciality.id = (data?.get("id") as Long).toInt()
                        speciality.name = data["name"] as String

                        specialityList.add(speciality)
                    }

                    onSuccess(specialityList)
                }
            }
    }

    override fun getConsultationRequestFromPatient(
        onSuccess: (consultation: List<ConsultationRequestVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("consultation_request")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it?.localizedMessage ?: "Please Check Internet")
                } ?: run {
                    val consultationRequestList : MutableList<ConsultationRequestVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()

                    for (document in result){
                        val data = document.data

                        var consultationRequestVO = ConsultationRequestVO()
                        consultationRequestVO.caseSummary = data?.get("case_summary") as CaseSummaryVO
                        consultationRequestVO.type = data["type"] as String
                        consultationRequestVO.patient = data["patient"] as PatientVO

                        consultationRequestList.add(consultationRequestVO)
                    }

                    onSuccess(consultationRequestList)
                }
            }
    }

    override fun getConsultationsForAPatient(
        patientId: String,
        onSuccess: (consultations: List<ConsultationVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("consultations")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.localizedMessage ?: "Please Check Internet")
                } ?: run {
                    val consultationList: MutableList<ConsultationVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()

                    for (document in result){
                        val data = document?.data

                        val consultationVO = ConsultationVO()

                        consultationVO.id = document.id
                        consultationVO.caseSummaryVO = data?.get("case_summary") as CaseSummaryVO
                        consultationVO.doctorVO = data["doctor"] as DoctorVO
                        consultationVO.patientVO = data["patient"] as PatientVO
                        consultationVO.type = data["type"] as String
                        consultationVO.prescriptionVO = data["prescription"] as PrescriptionVO

                        consultationList.add(consultationVO)
                    }

                    onSuccess(consultationList)
                }
            }
    }

    override fun getQuestionTemplateForDoctor(
        onSuccess: (doctors: List<DoctorVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        TODO("Not yet implemented")
    }
}