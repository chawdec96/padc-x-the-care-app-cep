package com.padcx.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class ConsultationRequestVO(
    var patient: PatientVO = PatientVO(),
    var type : String ?= "",
    var caseSummary: CaseSummaryVO = CaseSummaryVO()
)