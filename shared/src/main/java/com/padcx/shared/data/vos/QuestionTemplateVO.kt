package com.padcx.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
@Entity(tableName = "questionTemplate")
data class QuestionTemplateVO (
    @PrimaryKey(autoGenerate = false)
    var id: String = " ",
    var name: String ?= "",
    var question: String ?= "",
    var type: String ?= ""
)