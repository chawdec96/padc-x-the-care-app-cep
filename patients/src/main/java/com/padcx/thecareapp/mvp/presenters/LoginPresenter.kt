package com.padcx.thecareapp.mvp.presenters

import android.content.Context
import com.padcx.shared.BasePresenter
import com.padcx.thecareapp.mvp.views.LoginView

interface LoginPresenter: BasePresenter<LoginView> {

    fun onTapLogin(context: Context, email: String, password: String)
    fun onTapRegister()
}