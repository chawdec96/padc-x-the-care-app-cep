package com.padcx.thecareapp.mvp.presenters.impls

import android.content.Context
import com.padcx.shared.AbstractBasePresenter
import com.padcx.thecareapp.mvp.presenters.LoginPresenter
import com.padcx.thecareapp.mvp.views.LoginView

class LoginPresenterImpl: LoginPresenter, AbstractBasePresenter<LoginView>() {
    override fun onTapLogin(context: Context, email: String, password: String) {
        TODO("Not yet implemented")
    }

    override fun onTapRegister() {
        TODO("Not yet implemented")
    }
}