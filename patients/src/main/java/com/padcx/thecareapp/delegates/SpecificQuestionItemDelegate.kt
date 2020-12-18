package com.padcx.thecareapp.delegates

import com.padcx.shared.data.vos.SpecificQuestionVO

interface SpecificQuestionItemDelegate {

    fun onTapAnswerEditText(specificQuestionVO: SpecificQuestionVO, answer: String)

}