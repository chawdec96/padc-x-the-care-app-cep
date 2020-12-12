package com.padcx.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
@Entity(tableName = "doctor")
data class DoctorVO(
    @PrimaryKey(autoGenerate = false)
    var id: String = " ",
    var name: String ?= "",
    var email: String ?= "",
    var phone: String ?= "",
    var password: String ?= "",
    var image: String ?= "",
    var speciality: String ?= "",
    var experience: String ?= "",
    var doctorSignature: String ?= "",
    var degree: String = "",
    var university: String = ""
)