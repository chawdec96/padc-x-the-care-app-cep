package com.padcx.shared.data.models

import com.padcx.shared.data.vos.DoctorVO
import com.padcx.shared.data.vos.PatientVO
import com.padcx.shared.data.vos.SpecialityVO
import com.padcx.shared.network.FirebaseApi

interface TheCareAppModel {

    var mFirebaseApi: FirebaseApi

    fun getSpecialities(onSuccess: (specialities: List<SpecialityVO>) -> Unit, onFailure : (String) -> Unit)
    fun getDoctors(onSuccess: (doctors: List<DoctorVO>) -> Unit, onFailure: (String) -> Unit)
    fun getPatients(onSuccess: (patients: List<PatientVO>) -> Unit, onFailure: (String) -> Unit)

//    fun addCheckOut(
//        createdDate: String, type: String, sendAddress: String,
//    )
}