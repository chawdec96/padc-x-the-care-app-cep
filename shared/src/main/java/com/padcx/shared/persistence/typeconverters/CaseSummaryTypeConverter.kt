package com.padcx.shared.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcx.shared.data.vos.CaseSummaryVO

class CaseSummaryTypeConverter {

    @TypeConverter
    fun toString(caseSummary: CaseSummaryVO): String {
        return Gson().toJson(caseSummary)
    }

    @TypeConverter
    fun toCaseSummary(caseSummaryJsonString: String): CaseSummaryVO {
        val caseSummaryType = object : TypeToken<CaseSummaryVO>() {}.type
        return Gson().fromJson(caseSummaryJsonString, caseSummaryType)
    }
}