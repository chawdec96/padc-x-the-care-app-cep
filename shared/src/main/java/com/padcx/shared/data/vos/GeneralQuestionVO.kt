package com.padcx.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class GeneralQuestionVO(
    var id: String ?= "",
    var name: String ?= "",
    var answer: String ?= "",
    var type: String ?= ""
)