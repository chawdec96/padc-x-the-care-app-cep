package com.padcx.shared.data.models

import com.padcx.shared.data.vos.DoctorVO
import com.padcx.shared.data.vos.PatientVO
import com.padcx.shared.data.vos.SpecialityVO
import com.padcx.shared.network.FirebaseApi
import com.padcx.shared.network.impls.FirebaseCloudFirestoreApiImpl

object TheCareAppModelImpl: TheCareAppModel {
    override var mFirebaseApi: FirebaseApi = FirebaseCloudFirestoreApiImpl

    override fun getSpecialities(
        onSuccess: (specialities: List<SpecialityVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        TODO("Not yet implemented")
        //Get data and Save to database
    }

    override fun getDoctors(
        onSuccess: (doctors: List<DoctorVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        TODO("Not yet implemented")
        //Get data and Save to database
    }

    override fun getPatients(
        onSuccess: (patients: List<PatientVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        TODO("Not yet implemented")
        //Get data and Save to database
    }
}