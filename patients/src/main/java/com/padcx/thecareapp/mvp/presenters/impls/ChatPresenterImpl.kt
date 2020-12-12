package com.padcx.thecareapp.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.padcx.shared.mvp.presenters.AbstractBasePresenter
import com.padcx.thecareapp.mvp.presenters.ChatPresenter
import com.padcx.thecareapp.mvp.views.ChatView

class ChatPresenterImpl: ChatPresenter, AbstractBasePresenter<ChatView>() {
    override fun onUIReady(owner: LifecycleOwner) {
        TODO("Not yet implemented")
    }

    override fun onTapBtnSend(image: String, text: String) {
        TODO("create chat")
    }
}