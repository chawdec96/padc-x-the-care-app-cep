package com.padcx.shared.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcx.shared.data.vos.DoctorVO

class DoctorTypeConverter {

    @TypeConverter
    fun toString(doctor: DoctorVO): String {
        return Gson().toJson(doctor)
    }

    @TypeConverter
    fun toDoctor(doctorJsonString: String): DoctorVO {
        val doctorType = object : TypeToken<DoctorVO>() {}.type
        return Gson().fromJson(doctorJsonString, doctorType)
    }
}