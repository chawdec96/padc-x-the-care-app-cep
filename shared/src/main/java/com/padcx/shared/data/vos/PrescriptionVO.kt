package com.padcx.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class PrescriptionVO (
    var id : String ?= "",
    var medicineId: String ?= "",
    var count: Int = 0,
    var name: String ?= "",
    var price: Int = 0,
    var routine: RoutineVO
)