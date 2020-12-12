package com.padcx.doctors.mvp.views

import com.padcx.shared.mvp.views.BaseView

interface ChatView: BaseView {

    fun showMessage(message: String)
    fun showImage(image: String)

}