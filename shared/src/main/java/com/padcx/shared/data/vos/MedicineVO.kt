package com.padcx.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class MedicineVO(
    var name: String ?= "",
    var grams: Int ?= 0,
    var takingTime: String ?= "",
    var price: Int ?= 0
)