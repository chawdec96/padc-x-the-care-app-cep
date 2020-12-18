package com.padcx.doctors.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.doctors.R
import com.padcx.doctors.views.viewholders.SpecificQuestionWithAnsViewHolder
import com.padcx.shared.adapters.BaseAdapter
import com.padcx.shared.data.vos.CaseSummaryVO
import com.padcx.shared.views.viewholders.BaseViewHolder


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