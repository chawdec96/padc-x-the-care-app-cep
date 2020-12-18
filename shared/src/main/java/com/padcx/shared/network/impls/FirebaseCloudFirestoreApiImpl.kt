package com.padcx.shared.network.impls

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.padcx.shared.data.vos.*
import com.padcx.shared.network.FirebaseApi
import com.padcx.shared.utils.NODE_PATIENTS

object FirebaseCloudFirestoreApiImpl : FirebaseApi {

    private val db = Firebase.firestore

    override fun registeredPatient(
        email: String,
        password: String,
        username: String,
        phone: String,
        image: String,
        dob: String,
        height: String,
        bloodType: String,
        weight: String,
        bloodPressure: String,
        allergicMedicine: String
    ) {
        val patientMap = hashMapOf(
            "id" to "P$phone",
            "name" to username,
            "email" to email,
            "phone" to phone,
            "image" to image,
            "dob" to dob,
            "height" to height,
            "bloodType" to bloodType,
            "weight" to weight,
            "bloodPressure" to bloodPressure,
            "allergicMedicine" to allergicMedicine
        )

        db.collection("patients")
            .document("P$phone")
            .set(patientMap)
            .addOnSuccessListener { documentReference ->
//                val id = documentReference.id
//                db.collection("patients").document(id).update("pId", id)
                Log.d("Success", "Successfully added a patient")
            }
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
            "id" to "D$phone",
            "name" to username,
            "email" to email,
            "phone" to phone,
            "image" to image,
            "password" to password,
            "speciality" to speciality,
            "experience" to experience,
            "doctorSignature" to doctorSignature,
            "degree" to degree,
            "university" to university
        )

        db.collection("doctors")
            .document("D$phone")
            .set(doctorMap)
            .addOnSuccessListener { documentReference ->
//                val id = documentReference.id
//                db.collection("doctors").document(id).update("dId", id)
                Log.d("Success", "Successfully added a doctor")
            }
            .addOnFailureListener { Log.d("Failure", "Failed to add a doctor") }
    }

    /*
    override fun setConsultationRequestByPatient(
        caseSummaryVO: CaseSummaryVO,
        patientVO: PatientVO,
        specialityId: String
    ) {

        val consultationMap = hashMapOf(
            "speciality_id" to specialityId,
            "reqId" to "REQ${patientVO.phone}",
            "patient" to patientVO
        )

//        consultationMap["case_summary"] = caseSummaryData(caseSummaryVO)
//        consultationMap["patient"] = patientData(patientVO)

        db.collection("consultation_request")
            .document("REQ${patientVO.phone}")
            .set(consultationMap)
            .addOnSuccessListener { documentReference ->
//                val id = documentReference.id
//                //save id to SharedPreference
//                db.collection("consultation_request").document(id).update("consReqId", id)
                Log.d("Success", "Successfully added a Request")
            }
            .addOnFailureListener { Log.d("Failure", "Failed to add a Request") }


    }
     */

    override fun setConsultationRequest(consultationRequestVO: ConsultationRequestVO) {
        val consultationMap = hashMapOf(
            "specialityId" to consultationRequestVO.specialityId,
            "reqId" to consultationRequestVO.id,
            "patient" to consultationRequestVO.patient,
            "isAccept" to consultationRequestVO.isAccept
        )

        db.collection("consultation_request")
            .document(consultationRequestVO.id!!)
            .set(consultationMap)
            .addOnSuccessListener { Log.d("Success", "Successfully update a request") }
            .addOnFailureListener { Log.d("Failure", "Failed to update a request") }
    }

    override fun setCaseSummarySubCollection(
        nodeName: String, documentId: String, caseSummaryVO: CaseSummaryVO
    ) {
        val caseSummaryMap = hashMapOf(
            "sId" to caseSummaryVO.id,
            "question" to caseSummaryVO.question,
            "answer" to caseSummaryVO.answer,
            "type" to caseSummaryVO.type
        )

        db.collection(nodeName)
            .document(documentId)
            .collection("caseSummary")
            .document(caseSummaryVO.id!!)
            .set(caseSummaryMap)
            .addOnSuccessListener {  Log.d("Success", "Successfully add Case Summary")}
            .addOnFailureListener { Log.d ("Failure", "Failed to add Case Summary")}

    }

    override fun getCaseSummarySubCollection(
        documentId: String,
        onSuccess: (caseSummary: List<CaseSummaryVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("consultation_request/$documentId/caseSummary")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it?.localizedMessage)
                } ?: run {
                    val result = value?.documents ?: arrayListOf()

                    val caseSummaryList: MutableList<CaseSummaryVO> = arrayListOf()

                    for (document in result){
                        val data = document.data

                        val caseSummaryVO = CaseSummaryVO()

                        caseSummaryVO.id = data?.get("sId") as String
                        caseSummaryVO.type = data["type"] as String
                        caseSummaryVO.question = data["question"] as String
                        caseSummaryVO.answer = data["answer"] as String

                        caseSummaryList.add(caseSummaryVO)
                    }

                    onSuccess(caseSummaryList)
                }
            }
    }

    override fun setConsultationByDoctor(
        doctorVO: DoctorVO, patientVO: PatientVO, type: String, isStart: Boolean
    ) {
        val consultationMap = hashMapOf(
            "id" to "CON${doctorVO.phone}",
            "speciality_id" to type,
            "patient" to patientVO,
            "doctor" to doctorVO,
            "isStart" to isStart
        )

        consultationMap["patient"] = patientData(patientVO)

        db.collection("consultations")
            .document("CON${doctorVO.phone}")
            .set(consultationMap)
            .addOnSuccessListener { documentReference ->
//                val id = documentReference.id
//                db.collection("consultations").document(id).update("consId", id)
                Log.d("Success", "Successfully added a Consultation")
            }
            .addOnFailureListener { Log.d("Failure", "Failed to add a Consultation") }

    }

    override fun setCheckOutByPatient(
        deliveryRoutine: DeliveryRoutineVO,
        doctorVO: DoctorVO,
        patientVO: PatientVO,
        createdAt: String,
        sendAddress: String,
        specialityId: String
    ) {
        val checkOutMap = hashMapOf(
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
                Log.d("Success", "Successfully added a CheckOut")
            }
            .addOnFailureListener { Log.d("Failure", "Failed to add a CheckOut") }
    }

    override fun setRecentDoctorSubCollectionForAPatient(patientId: String, doctor: DoctorVO) {
        val doctorMap = doctorData(doctor)

        db.collection("patients")
            .document(patientId)
            .collection("recently_doctors")
            .add(doctorMap)
            .addOnSuccessListener { documentReference ->
                val id = documentReference.id
                db.collection("recently_doctors").document(id).update("rcDId", id)
                Log.d("Success", "Successfully added a CheckOut")
            }
            .addOnFailureListener { Log.d("Failure", "Failed to add a CheckOut") }
    }

    override fun setGeneralQuestionSubCollectionForAPatient(
        patientId: String,
        questionTemplateVO: GeneralQuestionVO
    ) {
        val questionMap = hashMapOf(
            "name" to questionTemplateVO.name,
            "answer" to questionTemplateVO.answer,
            "type" to questionTemplateVO.type
        )

        db.collection("patients")
            .document(patientId)
            .collection("general_questions")
            .document(questionTemplateVO.name!!)
            .set(questionMap)
            .addOnSuccessListener { Log.d("Success", "Successfully added general question") }
            .addOnFailureListener { Log.d("Failure", "Failed to add general question") }
    }

    override fun setSpecificQuestionsSubCollectionForAPatient(
        patientId: String,
        specificQuestion: SpecificQuestionVO
    ) {

        val map = hashMapOf(
            "id" to specificQuestion.id,
            "sentence" to specificQuestion.sentence
        )

        db.collection("patients")
            .document(patientId)
            .collection("specific_questions")
            .document(specificQuestion.id!!)
            .set(map)
            .addOnSuccessListener { Log.d("Success", "Successfully added general question") }
            .addOnFailureListener { Log.d("Failure", "Failed to add general question") }
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
                    for (document in result) {
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

    override fun getDoctor(
        email: String,
        onSuccess: (doctor: DoctorVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("doctors")
            .get()
            .addOnSuccessListener { result ->
                val doctor = DoctorVO()

                for (document in result) {
                    val data = document.data

                    val emailFromDB = data["email"] as String
                    if (email == emailFromDB) {
                        doctor.id = data["id"] as String
                        doctor.name = data["name"] as String
                        doctor.phone = data["phone"] as String
                        doctor.email = email
                        doctor.image = data["image"] as String
                        doctor.password = data["password"] as String
                        doctor.speciality = data["speciality"] as String
                        doctor.university = data["university"] as String
                        doctor.degree = data["degree"] as String
                        doctor.doctorSignature = data["doctorSignature"] as String
                    }
                }
                onSuccess(doctor)
            }
            .addOnFailureListener { exception ->
                onFailure(exception?.localizedMessage)
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

                    for (document in result) {
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

    override fun getPatient(
        email: String,
        onSuccess: (patient: PatientVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("patients")
            .get()
            .addOnSuccessListener { result ->
                val patientVO = PatientVO()

                for (document in result) {
                    val data = document.data

                    val emailFromDB = data["email"] as String
                    if (email == emailFromDB) {
                        patientVO.id = data["id"] as String
                        patientVO.username = data["name"] as String
                        patientVO.phone = data["phone"] as String
                        patientVO.email = email
                        patientVO.image = data["image"] as String
                        patientVO.dob = data["dob"] as String
                        patientVO.height = data["height"] as String
                        patientVO.bloodType = data["bloodType"] as String
                        patientVO.weight = data["weight"] as String
                        patientVO.bloodPressure = data["bloodPressure"] as String
                        patientVO.allergicMedicine = data["allergicMedicine"] as String
                    }
                }
                onSuccess(patientVO)
            }
            .addOnFailureListener { exception ->
                onFailure(exception?.localizedMessage)
            }
    }

    override fun getSpecialities(
        onSuccess: (specialities: List<SpecialityVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("specilities")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it?.localizedMessage ?: "Please Check Internet")
                } ?: run {
                    val specialityList: MutableList<SpecialityVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()

                    for (document in result) {
                        val data = document.data

                        val speciality = SpecialityVO()

                        speciality.id = data?.get("id") as String
                        speciality.name = data["name"] as String
                        speciality.image = data["image"] as String

                        specialityList.add(speciality)
                    }

                    onSuccess(specialityList)
                }
            }
    }

    override fun getConsultationRequestFromPatient(
        specialityId: String,
        onSuccess: (consultation: List<ConsultationRequestVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("consultation_request")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it?.localizedMessage ?: "Please Check Internet")
                } ?: run {
                    val consultationRequestList: MutableList<ConsultationRequestVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()

                    for (document in result) {
                        val data = document.data

                        if (specialityId == data?.get("specialityId") && data["isAccept"] == false){
                            val consultationRequestVO = ConsultationRequestVO()

                            val patientMap: Map<String, PatientVO> =
                                data["patient"] as Map<String, PatientVO>

                            consultationRequestVO.specialityId = data["specialityId"] as String

                            consultationRequestVO.patient = getPatientFromMap(patientMap)
//                            consultationRequestVO.isAccept = data["isAccept"] as Boolean

                            consultationRequestList.add(consultationRequestVO)
                        }
                    }
                    onSuccess(consultationRequestList)
                }
            }
    }

    private fun getPatientFromMap(patientMap: Map<String, PatientVO>): PatientVO {

        val patientVO = PatientVO()

        patientVO.email = patientMap["email"] as String
        patientVO.allergicMedicine = patientMap["allergicMedicine"] as String
        patientVO.bloodPressure = patientMap["bloodPressure"] as String
        patientVO.bloodType = patientMap["bloodType"] as String
        patientVO.dob = patientMap["dob"] as String
        patientVO.height = patientMap["height"] as String
        patientVO.weight = patientMap["weight"] as String
        patientVO.id = patientMap["id"] as String
//        patientVO.image = patientMap["image"] as String
        patientVO.username = patientMap["username"] as String
        patientVO.password = patientMap["password"] as String
        patientVO.phone = patientMap["phone"] as String

        return patientVO

    }

    private fun getCaseSummaryFromMap(caseSummaryMap: Map<String, CaseSummaryVO>): CaseSummaryVO {
        val caseSummary = CaseSummaryVO()

        caseSummary.allergicMedicine = caseSummaryMap["allergic_medicine"] as String
        caseSummary.bloodPressure = caseSummaryMap["blood_pressure"] as String
        caseSummary.bloodType = caseSummaryMap["blood_type"] as String
        caseSummary.case = caseSummaryMap["case"] as String
        caseSummary.height = caseSummaryMap["height"] as String
        caseSummary.weight = caseSummaryMap["weight"] as String
        caseSummary.dob = caseSummaryMap["dob"] as String

        return caseSummary

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

                    for (document in result) {
                        val data = document?.data

                        val consultationVO = ConsultationVO()

                        val patient = data?.get("patient") as PatientVO
                        if (patient.id == patientId) {
                            consultationVO.id = document.id
                            consultationVO.caseSummaryVO =
                                data?.get("case_summary") as CaseSummaryVO
                            consultationVO.doctorVO = data["doctor"] as DoctorVO
                            consultationVO.patientVO = patient
                            consultationVO.specialityId = data["type"] as String
                        }
                        consultationList.add(consultationVO)
                    }

                    onSuccess(consultationList)
                }
            }
    }

    override fun getConsultationsForADoctor(
        doctorId: String,
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

                    for (document in result) {
                        val data = document?.data

                        val consultationVO = ConsultationVO()

                        val doctorMap: Map<String, DoctorVO> = data?.get("doctor") as Map<String, DoctorVO>
                        val caseSummaryMap: Map<String, CaseSummaryVO> = data["case_summary"] as Map<String, CaseSummaryVO>
                        val patientMap: Map<String, PatientVO> = data["patient"] as Map<String, PatientVO>

//                        val doctor = data?.get("doctor") as DoctorVO
//                        val id = doctorMap["id"] as String
//                        if (id == doctorId) {
                            consultationVO.id = document.id
//                            consultationVO.caseSummaryVO = getCaseSummaryFromMap(caseSummaryMap)
                            consultationVO.doctorVO = getDoctorFromMap(doctorMap)
                            consultationVO.patientVO = getPatientFromMap(patientMap)
                            consultationVO.specialityId = data["speciality_id"] as String
//                        }
                        consultationList.add(consultationVO)
                    }

                    onSuccess(consultationList)
                }
            }
    }

    private fun getDoctorFromMap(doctorMap: Map<String, DoctorVO>): DoctorVO {
        val doctorVO = DoctorVO()

        doctorVO.degree = doctorMap["degree"] as String
//        doctorVO.doctorSignature = doctorMap["signature"] as String
        doctorVO.email = doctorMap["email"] as String
//        doctorVO.image = doctorMap["image"] as String
        doctorVO.name = doctorMap["name"] as String
        doctorVO.password = doctorMap["password"] as String
        doctorVO.phone = doctorMap["phone"] as String
        doctorVO.speciality = doctorMap["speciality"] as String
        doctorVO.university = doctorMap["university"] as String

        return doctorVO
    }

    override fun getQuestionTemplateForDoctor(
        onSuccess: (questions: List<QuestionTemplateVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("question_template")
            .get()
            .addOnSuccessListener { result ->

                val questionList: MutableList<QuestionTemplateVO> = arrayListOf()

                for (document in result) {
                    val data = document?.data

                    val questionTemplateVO = QuestionTemplateVO()

                    questionTemplateVO.id = data?.get("id") as String
                    questionTemplateVO.name = data["name"] as String
                    questionTemplateVO.question = data["question"] as String
                    questionTemplateVO.type = data["type"] as String

                    questionList.add(questionTemplateVO)
                }

                onSuccess(questionList)
            }
    }

    override fun getCheckOutForAPatient(
        patientId: String,
        onSuccess: (checkOut: List<CheckOutVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("check_out")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it?.localizedMessage ?: "Please Check Internet")
                } ?: run {
                    val result = value?.documents ?: arrayListOf()

                    val checkOutList: MutableList<CheckOutVO> = arrayListOf()

                    for (document in result) {
                        val data = document?.data

                        val checkOutVO = CheckOutVO()
                        val patient: PatientVO = data?.get("patient") as PatientVO

                        if (patient.id == patientId) {
                            checkOutVO.id = document.id
                            checkOutVO.patient = patient
                            checkOutVO.doctor = data["doctor"] as DoctorVO
                            checkOutVO.createdDate = data["created_date"] as String
                            checkOutVO.deliveryAddr = data["delivery_address"] as String
                            checkOutVO.deliveryRoutine =
                                data["delivery_routine"] as DeliveryRoutineVO
                        }

                        checkOutList.add(checkOutVO)
                    }

                    onSuccess(checkOutList)

                }

            }
    }

    override fun getPrescriptions(
        noteName: String,
        documentId: String,
        onSuccess: (prescriptions: List<PrescriptionVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("$noteName/$documentId/prescription")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it?.localizedMessage ?: "Please Check Internet Connection")
                } ?: run {
                    val result = value?.documents ?: arrayListOf()

                    val prescriptionList: MutableList<PrescriptionVO> = arrayListOf()

                    for (document in result) {
                        val prescription = PrescriptionVO()

                        val data = document?.data

                        prescription.id = document.id
                        prescription.count = (data?.get("count") as Long).toInt()
                        prescription.medicineId = data["medicine_id"] as String
                        prescription.name = data["name"] as String
                        prescription.price = (data["price"] as Long).toInt()
                        prescription.routine = data["routine"] as RoutineVO

                        prescriptionList.add(prescription)
                    }
                    onSuccess(prescriptionList)
                }
            }
    }

    override fun getChats(
        documentId: String,
        onSuccess: (chats: List<ChatVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("consultations/$documentId/chat")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.localizedMessage ?: "Please Check Internet")
                } ?: run {
                    val chatList: MutableList<ChatVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()
                    for (document in result) {
                        val chat = ChatVO()

                        val data = document.data
                        chat.id = document.id
                        chat.date = data?.get("date") as String
                        chat.image = data["image"] as String
                        chat.sendTime = data["send_time"] as String
                        chat.sender = data["sender"] as String
                        chat.text = data["text"] as String

                        chatList.add(chat)

                    }
                    onSuccess(chatList)
                }
            }
    }

    override fun getMedicines(
        documentId: String,
        onSuccess: (medicines: List<MedicineVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("specilities/$documentId/medicines")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.localizedMessage ?: "Please Check Internet")
                } ?: run {
                    val medicineList: MutableList<MedicineVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()
                    for (document in result) {
                        val medicine = MedicineVO()

                        val data = document.data

                        medicine.id = document.id
                        medicine.name = data?.get("name") as String
                        medicine.price = (data["price"] as Long).toInt()

                        medicineList.add(medicine)
                    }
                    onSuccess(medicineList)
                }
            }
    }

    override fun getSpecificQuestions(
        documentId: String,
        onSuccess: (specificQuestions: List<SpecificQuestionVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("specilities/$documentId/specific_questions")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.localizedMessage ?: "Please Check Internet")
                } ?: run {
                    val questionList: MutableList<SpecificQuestionVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()
                    for (document in result) {
                        val question = SpecificQuestionVO()

                        val data = document?.data

                        question.id = document.id
                        question.sentence = data?.get("sentence") as String
                        question.type = data["type"] as String

                        questionList.add(question)
                    }
                    onSuccess(questionList)
                }
            }
    }

    override fun getRecentDoctorsForAPatient(
        patientId: String,
        onSuccess: (doctors: List<DoctorVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("$NODE_PATIENTS/$patientId/recently_doctors")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.localizedMessage ?: "Please Check Internet")
                } ?: run {
                    val result = value?.documents ?: arrayListOf()

                    val doctorList: MutableList<DoctorVO> = arrayListOf()

                    for (document in result) {
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

    override fun getGeneralQuestionsForAPatient(
        patientId: String,
        onSuccess: (questions: List<GeneralQuestionVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("$NODE_PATIENTS/$patientId/general_questions")
            .get()
            .addOnSuccessListener { result ->

                val questionList: MutableList<GeneralQuestionVO> = arrayListOf()

                for (document in result) {
                    val data = document?.data

                    val questionTemplateVO = GeneralQuestionVO()

//                    questionTemplateVO.id = data?.get("id") as String
                    questionTemplateVO.name = data?.get("name") as String
                    questionTemplateVO.answer = data["answer"] as String
                    questionTemplateVO.type = data["type"] as String

                    questionList.add(questionTemplateVO)
                }

                onSuccess(questionList)
            }
    }

    override fun getSpecificQuestionsForAPatient(
        patientId: String,
        onSuccess: (questions: List<SpecificQuestionVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("$NODE_PATIENTS/$patientId/specific_questions")
            .get()
            .addOnSuccessListener { result ->

                val questionList: MutableList<SpecificQuestionVO> = arrayListOf()

                for (document in result) {
                    val data = document?.data

                    val specificQuestionVO = SpecificQuestionVO()

                    specificQuestionVO.id = data?.get("id") as String
                    specificQuestionVO.sentence = data?.get("sentence") as String
                    specificQuestionVO.type = data["type"] as String

                    questionList.add(specificQuestionVO)
                }

                onSuccess(questionList)
            }
    }

    override fun setPrescriptionSubCollection(
        nodeName: String,
        documentId: String,
        prescription: PrescriptionVO
    ) {

        val prescriptionMap = hashMapOf(
            "count" to prescription.count,
            "medicine_id" to prescription.medicineId,
            "name" to prescription.name,
            "price" to prescription.price,
            "routine" to prescription.routine
        )


        db.collection(nodeName)
            .document(documentId)
            .collection("prescription")
            .add(prescriptionMap)
            .addOnSuccessListener { documentReference ->
                val id = documentReference.id
                db.collection("prescription").document(id).update("pres", id)
                Log.d("Success", "Successfully added a Request")
            }
            .addOnFailureListener { Log.d("Failure", "Failed to add a Request") }
    }

    override fun setChatSubCollectionToConsultation(consultationId: String, chatVO: ChatVO) {
        val chatMap = hashMapOf(
            "date" to chatVO.date,
            "image" to chatVO.image,
            "send_time" to chatVO.sendTime,
            "sender" to chatVO.sender,
            "text" to chatVO.text
        )

        db.collection("consultations")
            .document(consultationId)
            .collection("chat")
            .add(chatMap)
            .addOnSuccessListener { documentReference ->
                val id = documentReference.id
                db.collection("chat").document(id).update("chat", id)
                Log.d("Success", "Successfully added a Request")
            }
            .addOnFailureListener { Log.d("Failure", "Failed to add a Request") }
    }

    private fun caseSummaryData(caseSummary: CaseSummaryVO): Any {
        return hashMapOf(
            "allergic_medicine" to caseSummary.allergicMedicine,
            "blood_pressure" to caseSummary.bloodPressure,
            "blood_type" to caseSummary.bloodType,
            "dob" to caseSummary.dob,
            "height" to caseSummary.height,
            "weight" to caseSummary.weight
        )
    }

    private fun patientData(patientVO: PatientVO): Any {
        return hashMapOf(
            "id" to patientVO.id,
            "email" to patientVO.email,
            "name" to patientVO.username,
            "password" to patientVO.password,
            "phone" to patientVO.phone
        )
    }

    private fun doctorData(doctorVO: DoctorVO): Any {
        return hashMapOf(
            "name" to doctorVO.name,
            "email" to doctorVO.email,
            "phone" to doctorVO.phone,
            "image" to doctorVO.image,
            "speciality" to doctorVO.speciality,
            "experience" to doctorVO.experience,
            "doctorSignature" to doctorVO.doctorSignature,
            "degree" to doctorVO.degree,
            "university" to doctorVO.university
        )
    }


}