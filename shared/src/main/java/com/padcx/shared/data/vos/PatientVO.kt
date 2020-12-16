package com.padcx.shared.data.vos

import com.google.firebase.firestore.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class PatientVO(
    var id: String = "",
    var username: String ?= "",
    var email: String ?= "",
    var password: String ?= "",
    var phone: String ?= "",
    var image: String ?= "",
    var dob: String ?= "",
    var height: String ?= "",
    var bloodType: String ?= "",
    var weight: String ?= "",
    var bloodPressure: String ?= "",
    var allergicMedicine: String ?= "",
    var recentDoctor: ArrayList<DoctorVO> ?= arrayListOf(),
    var generalQuestions: ArrayList<GeneralQuestionVO> ?= arrayListOf(),
    var specificQuestions: ArrayList<SpecificQuestionVO> ?= arrayListOf()
): Serializable