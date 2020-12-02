package com.padcx.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class DeliveryRoutine(
    var deliveryDate: String? = "",
    var deliveryTime: String? = ""
)