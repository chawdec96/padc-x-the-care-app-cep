package com.padcx.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class CheckOutVO(
    var deliveryRoutine: DeliveryRoutineVO,
    var doctor: DoctorVO,
    var patient: PatientVO,
    var prescription: PrescriptionVO,
    var createdDate: String ?= "",
    var deliveryAddr: String ?= "",
    var type: String ?= ""
)