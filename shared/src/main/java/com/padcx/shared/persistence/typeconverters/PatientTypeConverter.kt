package com.padcx.shared.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcx.shared.data.vos.PatientVO

class PatientTypeConverter {

    @TypeConverter
    fun toString(patient: PatientVO): String {
        return Gson().toJson(patient)
    }

    @TypeConverter
    fun toPatient(patientJsonString: String): PatientVO {
        val patientType = object : TypeToken<PatientVO>() {}.type
        return Gson().fromJson(patientJsonString, patientType)
    }
}