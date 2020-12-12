package com.padcx.shared.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcx.shared.data.vos.QuestionTemplateVO

class SpecificQuestionTypeConverter {
    @TypeConverter
    fun toString(questionList: ArrayList<QuestionTemplateVO>): String {
        return Gson().toJson(questionList)
    }

    @TypeConverter
    fun toQuestionTemplateList(questionListJsonString: String): ArrayList<QuestionTemplateVO> {
        val questionListType = object : TypeToken<ArrayList<QuestionTemplateVO>>() {}.type
        return Gson().fromJson(questionListJsonString, questionListType)
    }
}