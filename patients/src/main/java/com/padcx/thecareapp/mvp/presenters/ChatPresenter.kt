package com.padcx.thecareapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcx.shared.mvp.presenters.BasePresenter
import com.padcx.thecareapp.mvp.views.ChatView

interface ChatPresenter: BasePresenter<ChatView> {

    fun onUIReady(owner: LifecycleOwner)
    fun onTapBtnSend(image: String, text: String)

}