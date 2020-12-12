package com.padcx.thecareapp.mvp.presenters

import android.content.Context
import com.padcx.shared.BasePresenter
import com.padcx.thecareapp.mvp.views.RegisterView

interface RegisterPresenter: BasePresenter<RegisterView> {

    fun onTapLogin()
    fun onTapRegister(
        context: Context, email: String,
        password: String, phone: String,
        image: String
    )
}