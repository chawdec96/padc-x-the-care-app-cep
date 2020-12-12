package com.padcx.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class SpecialityVO(
    var id: Int = 0,
    var name: String ?= "",
    var specificQuestions: List<String> ?= arrayListOf(),
    var medicines: List<String> ?= arrayListOf()
)