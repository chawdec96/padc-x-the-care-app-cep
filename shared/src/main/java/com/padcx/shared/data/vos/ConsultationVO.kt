package com.padcx.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class Consultation (
    var caseSummaryVO: CaseSummaryVO,
    var doctorVO: DoctorVO = DoctorVO(),
    var patientVO: PatientVO = PatientVO(),
    var prescriptionVO: PrescriptionVO = PrescriptionVO(),
    var type : String ?= "",
    var chat
)