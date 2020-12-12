package com.padcx.shared.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcx.shared.data.vos.MedicineVO

class MedicineTypeConverter {
    @TypeConverter
    fun toString(medicineList: ArrayList<MedicineVO>): String {
        return Gson().toJson(medicineList)
    }

    @TypeConverter
    fun toMedicineList(medicineListJsonString: String): ArrayList<MedicineVO> {
        val medicineListType = object : TypeToken<ArrayList<MedicineVO>>() {}.type
        return Gson().fromJson(medicineListJsonString, medicineListType)
    }
}