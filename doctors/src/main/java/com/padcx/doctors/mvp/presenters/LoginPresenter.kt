package com.padcx.doctors.mvp.presenters

import com.padcx.doctors.mvp.views.LoginView
import com.padcx.shared.mvp.presenters.BasePresenter

interface LoginPresenter: BasePresenter<LoginView> {

    fun onTapLogin(email: String, password: String)
    fun onTapRegister()
}