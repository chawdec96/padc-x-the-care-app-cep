package com.padcx.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class ConsultationRequestVO(
    var id: String ?= "",
    var patient: PatientVO = PatientVO(),
    var specialityId : String ?= "",
    var caseSummary: ArrayList<CaseSummaryVO> = arrayListOf(),
    var isAccept: Boolean ?= false
): Serializable