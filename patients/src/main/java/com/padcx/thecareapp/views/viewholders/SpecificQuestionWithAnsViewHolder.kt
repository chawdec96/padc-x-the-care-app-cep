package com.padcx.thecareapp.views.viewholders

import android.view.View
import com.padcx.shared.data.vos.SpecificQuestionVO
import com.padcx.shared.views.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_specific_question_with_answer.view.*

class SpecificQuestionWithAnsViewHolder(itemView: View) :
    BaseViewHolder<SpecificQuestionVO>(itemView) {
    override fun bindData(data: SpecificQuestionVO) {
        itemView.tvQuestion.text = data.sentence
        itemView.tvAnswer.text = data.answer
    }
}