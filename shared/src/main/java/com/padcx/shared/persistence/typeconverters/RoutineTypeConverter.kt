package com.padcx.shared.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcx.shared.data.vos.RoutineVO

class RoutineTypeConverter {

    @TypeConverter
    fun toString(routine: RoutineVO): String {
        return Gson().toJson(routine)
    }

    @TypeConverter
    fun toRoutine(routineJsonString: String): RoutineVO {
        val routineType = object : TypeToken<RoutineVO>() {}.type
        return Gson().fromJson(routineJsonString, routineType)
    }
}