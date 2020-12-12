package com.padcx.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
@Entity(tableName = "checkOut")
data class CheckOutVO(
    @PrimaryKey(autoGenerate = false)
    var id: String = " ",
    var deliveryRoutine: DeliveryRoutineVO = DeliveryRoutineVO(),
    var doctor: DoctorVO = DoctorVO(),
    var patient: PatientVO = PatientVO(),
    var prescriptionList: ArrayList<PrescriptionVO> ?= arrayListOf(),
    var createdDate: String ?= "",
    var deliveryAddr: String ?= "",
    var specialityId: String ?= ""
)