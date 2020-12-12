package com.padcx.thecareapp.mvp.presenters

import com.padcx.shared.mvp.presenters.BasePresenter
import com.padcx.thecareapp.mvp.views.LoginView

interface LoginPresenter:
    BasePresenter<LoginView> {

    fun onTapLogin(email: String, password: String)
    fun onTapRegister()
}