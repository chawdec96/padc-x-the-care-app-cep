package com.padcx.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class CaseSummaryVO (
    var id: String ?= "",
    var question: String ?= "",
    var answer: String ?= "",
    var type: String ?= "",
    var allergicMedicine: String ?= "",
    var bloodPressure: String ?= "",
    var bloodType: String ?= "",
    var dob: String ?= "",
    var height: String ?= "",
    var weight: String ?= "",
    var case: String ?= ""
)