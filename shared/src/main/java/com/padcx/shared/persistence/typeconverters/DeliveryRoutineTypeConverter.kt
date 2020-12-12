package com.padcx.shared.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcx.shared.data.vos.DeliveryRoutineVO
import com.padcx.shared.data.vos.DoctorVO

class DeliveryRoutineTypeConverter {

    @TypeConverter
    fun toString(deliveryRoutine: DeliveryRoutineVO): String {
        return Gson().toJson(deliveryRoutine)
    }

    @TypeConverter
    fun toDeliveryRoutine(deliveryRoutineJsonString: String): DeliveryRoutineVO {
        val deliveryRoutineType = object : TypeToken<DeliveryRoutineVO>() {}.type
        return Gson().fromJson(deliveryRoutineJsonString, deliveryRoutineType)
    }
}