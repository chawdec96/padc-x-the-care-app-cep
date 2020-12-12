package com.padcx.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
@Entity(tableName = "speciality")
data class SpecialityVO(
    @PrimaryKey(autoGenerate = false)
    var id: String = " ",
    var name: String ?= "",
    var image: String ?= "",
    var specificQuestions: ArrayList<SpecificQuestionVO> ?= arrayListOf(),
    var medicines: ArrayList<MedicineVO> ?= arrayListOf()
): Serializable