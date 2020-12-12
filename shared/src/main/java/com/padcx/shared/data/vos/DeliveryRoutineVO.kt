package com.padcx.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class DeliveryRoutineVO(
    var deliveryDate: String? = "",
    var deliveryTime: String? = ""
)