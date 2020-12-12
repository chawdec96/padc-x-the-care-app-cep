package com.padcx.thecareapp.mvp.presenters.impls

import android.content.Context
import com.padcx.shared.AbstractBasePresenter
import com.padcx.thecareapp.mvp.presenters.RegisterPresenter
import com.padcx.thecareapp.mvp.views.RegisterView

class RegisterPresenterImpl: RegisterPresenter, AbstractBasePresenter<RegisterView>() {
    override fun onTapLogin() {
        TODO("Not yet implemented")
    }

    override fun onTapRegister(
        context: Context,
        email: String,
        password: String,
        phone: String,
        image: String
    ) {
        TODO("Not yet implemented")
    }
}