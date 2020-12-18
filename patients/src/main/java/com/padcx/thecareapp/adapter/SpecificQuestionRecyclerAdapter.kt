package com.padcx.thecareapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.shared.adapters.BaseAdapter
import com.padcx.shared.data.vos.SpecificQuestionVO
import com.padcx.shared.views.viewholders.BaseViewHolder
import com.padcx.thecareapp.R
import com.padcx.thecareapp.delegates.SpecificQuestionItemDelegate
import com.padcx.thecareapp.views.viewholders.SpecificQuestionViewHolder
import com.padcx.thecareapp.views.viewholders.SpecificQuestionWithAnsViewHolder

class SpecificQuestionRecyclerAdapter(private val delegate: SpecificQuestionItemDelegate) :
    BaseAdapter<BaseViewHolder<SpecificQuestionVO>, SpecificQuestionVO>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<SpecificQuestionVO> {
        return SpecificQuestionViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_specific_question, parent, false),
            delegate
        )
    }
}