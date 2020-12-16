package com.padcx.thecareapp.views.viewholders

import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import com.padcx.shared.data.vos.SpecificQuestionVO
import com.padcx.shared.views.viewholders.BaseViewHolder
import com.padcx.thecareapp.delegates.SpecificQuestionItemDelegate
import kotlinx.android.synthetic.main.item_specific_question.view.*

class SpecificQuestionViewHolder(itemView: View, private val delegate: SpecificQuestionItemDelegate) : BaseViewHolder<SpecificQuestionVO>(itemView) {
    override fun bindData(data: SpecificQuestionVO) {

        itemView.tvQuestion.text = data.sentence
        itemView.etAnswer.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                actionId == EditorInfo.IME_ACTION_DONE ||
                keyEvent != null &&
                keyEvent.action == KeyEvent.ACTION_DOWN &&
                keyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
                if (keyEvent == null || !keyEvent.isShiftPressed) {
                    // the user is done typing.
                    data.answer = itemView.etAnswer.text.toString()
                    delegate.onTapAnswerEditText(data)
                    true // consume.
                }
            }
            false
        }
    }
}