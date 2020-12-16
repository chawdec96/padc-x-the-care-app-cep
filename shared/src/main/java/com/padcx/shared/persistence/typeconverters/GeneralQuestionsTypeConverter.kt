package com.padcx.shared.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcx.shared.data.vos.GeneralQuestionVO
import com.padcx.shared.data.vos.QuestionTemplateVO

class GeneralQuestionsTypeConverter {
    @TypeConverter
    fun toString(questionList: ArrayList<GeneralQuestionVO>): String {
        return Gson().toJson(questionList)
    }

    @TypeConverter
    fun toQuestionTemplateList(questionListJsonString: String): ArrayList<GeneralQuestionVO> {
        val questionListType = object : TypeToken<ArrayList<GeneralQuestionVO>>() {}.type
        return Gson().fromJson(questionListJsonString, questionListType)
    }
}