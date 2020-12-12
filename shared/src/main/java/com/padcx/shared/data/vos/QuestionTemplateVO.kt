package com.padcx.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class QuestionTemplateVO (
    var id: String ?= "",
    var name: String ?= "",
    var question: String ?= "",
    var type: String ?= ""
)