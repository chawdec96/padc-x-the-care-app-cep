package com.padcx.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class PatientVO(
    var id: String = "",
    var username: String ?= "",
    var email: String ?= "",
    var password: String ?= "",
    var phone: String ?= "",
    var image: String ?= "",
    var recentDoctor: ArrayList<DoctorVO> ?= arrayListOf(),
    var generalQuestions: ArrayList<QuestionTemplateVO> ?= arrayListOf()
)