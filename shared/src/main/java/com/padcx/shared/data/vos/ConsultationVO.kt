package com.padcx.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
@Entity(tableName = "consultation")
data class ConsultationVO (
    @PrimaryKey(autoGenerate = false)
    var id: String = " ",
    var caseSummaryVO: CaseSummaryVO = CaseSummaryVO(),
    var doctorVO: DoctorVO = DoctorVO(),
    var patientVO: PatientVO = PatientVO(),
    var prescriptionList: ArrayList<PrescriptionVO> ?= arrayListOf(),
    var specialityId : String ?= "",
    var chat: ArrayList<ChatVO> ?= arrayListOf()
)