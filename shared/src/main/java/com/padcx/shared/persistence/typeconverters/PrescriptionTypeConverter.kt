package com.padcx.shared.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcx.shared.data.vos.PrescriptionVO

class PrescriptionTypeConverter {

    @TypeConverter
    fun toString(prescriptionList: ArrayList<PrescriptionVO>): String {
        return Gson().toJson(prescriptionList)
    }

    @TypeConverter
    fun toPrescriptionList(prescriptionListJsonString: String): ArrayList<PrescriptionVO> {
        val prescriptionListType = object : TypeToken<ArrayList<PrescriptionVO>>() {}.type
        return Gson().fromJson(prescriptionListJsonString, prescriptionListType)
    }
}