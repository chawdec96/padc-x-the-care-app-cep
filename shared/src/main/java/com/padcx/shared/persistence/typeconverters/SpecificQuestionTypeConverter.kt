package com.padcx.shared.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcx.shared.data.vos.QuestionTemplateVO
import com.padcx.shared.data.vos.SpecificQuestionVO

class SpecificQuestionTypeConverter {
    @TypeConverter
    fun toString(questionList: ArrayList<SpecificQuestionVO>): String {
        return Gson().toJson(questionList)
    }

    @TypeConverter
    fun toSpecificQuestionList(questionListJsonString: String): ArrayList<SpecificQuestionVO> {
        val questionListType = object : TypeToken<ArrayList<SpecificQuestionVO>>() {}.type
        return Gson().fromJson(questionListJsonString, questionListType)
    }
}