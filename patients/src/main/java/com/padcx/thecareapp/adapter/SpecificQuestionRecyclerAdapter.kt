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

class SpecificQuestionRecyclerAdapter(private val delegate: SpecificQuestionItemDelegate, private val type: String):
    BaseAdapter<BaseViewHolder<SpecificQuestionVO>, SpecificQuestionVO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<SpecificQuestionVO> {
        when (type){
            "question" -> {
                return SpecificQuestionViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_specific_question, parent, false),
                    delegate
                )
            }
            "answer" -> {
                return SpecificQuestionWithAnsViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_specific_question_with_answer, parent, false)
                )
            }
            else ->{
                return SpecificQuestionViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_specific_question, parent, false),
                    delegate
                )
            }
        }

    }
}