package com.padcx.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class MedicineVO(
    var id: String = "",
    var name: String ?= "",
    var price: Int ?= 0
)