package com.padcx.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class SpecificQuestionVO(
    var id: String ?= "",
    var sentence: String ?= "",
    var type: String ?= ""
)