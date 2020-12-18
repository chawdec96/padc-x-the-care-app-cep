package com.padcx.thecareapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.shared.adapters.BaseAdapter
import com.padcx.shared.data.vos.CaseSummaryVO
import com.padcx.shared.data.vos.SpecificQuestionVO
import com.padcx.shared.views.viewholders.BaseViewHolder
import com.padcx.thecareapp.R
import com.padcx.thecareapp.delegates.SpecificQuestionItemDelegate
import com.padcx.thecareapp.views.viewholders.SpecificQuestionViewHolder
import com.padcx.thecareapp.views.viewholders.SpecificQuestionWithAnsViewHolder

class CaseSummaryRecyclerAdapter :
    BaseAdapter<BaseViewHolder<CaseSummaryVO>, CaseSummaryVO>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<CaseSummaryVO> {
        return SpecificQuestionWithAnsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_specific_question_with_answer, parent, false)
        )
    }
}