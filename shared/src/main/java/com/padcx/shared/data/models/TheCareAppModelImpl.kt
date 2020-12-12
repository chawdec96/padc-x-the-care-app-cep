package com.padcx.shared.data.models

import androidx.lifecycle.LiveData
import com.padcx.shared.data.vos.*
import com.padcx.shared.network.FirebaseApi
import com.padcx.shared.network.impls.FirebaseCloudFirestoreApiImpl

object TheCareAppModelImpl : TheCareAppModel, BaseModel() {

    override var mFirebaseApi: FirebaseApi = FirebaseCloudFirestoreApiImpl

    override fun getSpecialitiesFromFirebaseAndSaveToDB(
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getSpecialities(onSuccess = {
            mTheCareDB.theCareDao().insertSpecialities(it)
        }, onFailure = {
            onFailure(it)
        })
    }

    override fun getSpecialities(): LiveData<List<SpecialityVO>> {
        return mTheCareDB.theCareDao().getSpecialities()
    }

    override fun setDoctor(doctorVO: DoctorVO) {
//        mFirebaseApi.setDoctor(doctorVO)
    }

    override fun getDoctors(
        onSuccess: (doctors: List<DoctorVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getDoctors(onSuccess = {
            mTheCareDB.patientDao().insertDoctors(it)
        }, onFailure = {

        })
    }

    override fun setPatient(
        email: String, password: String, username: String, phone: String, image: String
    ) {

        mFirebaseApi.registeredPatient(email, password, username, phone, image)
    }

    override fun getPatients(
        onSuccess: (patients: List<PatientVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        TODO("Not yet implemented")
        //Get data and Save to database
    }

    override fun getQuestionsTemplateFromFirebaseAndSaveToDB(
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getQuestionTemplateForDoctor(onSuccess = {
            mTheCareDB.doctorDao().insertQuestionTemplate(it)
        }, onFailure = {
            onFailure(it)
        })
    }

    override fun getQuestionsTemplate(): LiveData<List<QuestionTemplateVO>> {
        return mTheCareDB.doctorDao().getQuestionTemplates()
    }

    override fun setChatMessage(consultationId: String, chatVO: ChatVO) {
        mFirebaseApi.setChatSubCollectionToConsultation(consultationId, chatVO)
    }

    override fun getChatMessage(
        consultationId: String,
        onSuccess: (chats: List<ChatVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getChats(consultationId, onSuccess, onFailure)
    }

    override fun addConsultationRequestByPatient(
        caseSummaryVO: CaseSummaryVO,
        patients: PatientVO,
        type: String
    ) {
        mFirebaseApi.setConsultationRequestByPatient(
            caseSummaryVO, patients, type
        )
    }

    override fun getConsultationRequestFromPatient(
        onSuccess: (consultationRequest: List<ConsultationRequestVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getConsultationRequestFromPatient(onSuccess = {
            // TODO request consultations
        }, onFailure = {
            onFailure(it)
        })
    }

    override fun addConsultationByDoctor(
        caseSummaryVO: CaseSummaryVO,
        doctorVO: DoctorVO,
        patients: PatientVO,
        prescriptionVO: List<PrescriptionVO>,
        type: String,
        chat: List<ChatVO>
    ) {
        mFirebaseApi.setConsultationByDoctor(
            caseSummaryVO,
            doctorVO,
            patients,
            prescriptionVO,
            type,
            chat
        )
    }

    override fun setRecentlyDoctors(patientId: String, doctorVO: DoctorVO) {
        mFirebaseApi.setRecentDoctorSubCollectionForAPatient(patientId, doctorVO)
    }

    override fun getRecentlyDoctors(
        patientId: String,
        onSuccess: (doctors: List<DoctorVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getRecentDoctorsForAPatient(patientId, onSuccess, onFailure)
    }

    override fun getConsultationsForAPatientFromFirebaseAndSaveToDB(
        patientId: String,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getConsultationsForAPatient(patientId, onSuccess = {
            mTheCareDB.patientDao().insertConsultations(it)
        }, onFailure = {
            onFailure(it)
        })
    }

    override fun getConsultationsForADoctorFromFirebaseAndSaveToDB(
        doctorId: String,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getConsultationsForADoctor(doctorId, onSuccess = {
            mTheCareDB.doctorDao().insertConsultations(it)
        }, onFailure = {
            onFailure(it)
        })
    }

    override fun getConsultations(): LiveData<List<ConsultationVO>> {
        return mTheCareDB.doctorDao().getConsultations()
    }

    override fun addCheckOutByPatient(
        deliveryRoutineVO: DeliveryRoutineVO,
        doctorVO: DoctorVO,
        patientVO: PatientVO,
        createdAt: String,
        sendAddress: String,
        specialityId: String
    ) {
        mFirebaseApi.setCheckOutByPatient(
            deliveryRoutineVO, doctorVO,
            patientVO, createdAt, sendAddress, specialityId
        )
    }

    override fun getCheckOutsFromFirebaseAndSaveToDB(
        patientId: String,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getCheckOutForAPatient(patientId, onSuccess = {
            mTheCareDB.patientDao().insertCheckOuts(it)
        }, onFailure = {
            onFailure(it)
        })
    }

    override fun getCheckOuts(): LiveData<List<CheckOutVO>> {
        return mTheCareDB.patientDao().getCheckOuts()
    }

    override fun setPrescriptionMedicine(
        nodeName: String,
        documentId: String,
        prescriptionVO: PrescriptionVO
    ) {
        mFirebaseApi.setPrescriptionSubCollection(nodeName, documentId, prescriptionVO)
    }

    override fun getPrescriptionMedicines(
        nodeName: String,
        documentId: String,
        onSuccess: (prescriptions: List<PrescriptionVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getPrescriptions(nodeName, documentId, onSuccess, onFailure)
    }

}