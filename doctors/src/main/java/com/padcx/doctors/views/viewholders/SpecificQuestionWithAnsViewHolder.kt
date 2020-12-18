package com.padcx.doctors.views.viewholders

import android.view.View
import com.padcx.shared.data.vos.CaseSummaryVO
import com.padcx.shared.views.viewholders.BaseViewHolder
import kotlinx.android.synthetic.main.item_specific_question_with_answer.view.*

class SpecificQuestionWithAnsViewHolder(itemView: View) :
    BaseViewHolder<CaseSummaryVO>(itemView) {
    override fun bindData(data: CaseSummaryVO) {
        itemView.tvQuestion.text = data.question
        itemView.tvAnswer.text = data.answer
    }
}