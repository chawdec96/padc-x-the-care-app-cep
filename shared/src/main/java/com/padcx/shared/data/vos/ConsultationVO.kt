package com.padcx.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class ConsultationVO (
    var id: String ?= "",
    var caseSummaryVO: CaseSummaryVO = CaseSummaryVO(),
    var doctorVO: DoctorVO = DoctorVO(),
    var patientVO: PatientVO = PatientVO(),
    var prescriptionVO: PrescriptionVO = PrescriptionVO(),
    var type : String ?= "",
    var chat: List<ChatVO> ?= arrayListOf()
)