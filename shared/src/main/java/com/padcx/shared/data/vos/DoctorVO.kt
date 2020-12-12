package com.padcx.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class DoctorVO(
    var id: String ?= "",
    var name: String ?= "",
    var email: String ?= "",
    var phone: String ?= "",
    var password: String ?= "",
    var image: String ?= "",
    var speciality: String ?= "",
    var experience: String ?= "",
    var doctorSignature: String ?= "",
    var degree: String = "",
    var university: String = ""
)