package com.padcx.thecareapp.mvp.presenters.impls

import com.padcx.shared.data.models.AuthenticationModel
import com.padcx.shared.data.models.AuthenticationModelImpl
import com.padcx.shared.mvp.presenters.AbstractBasePresenter
import com.padcx.thecareapp.mvp.presenters.LoginPresenter
import com.padcx.thecareapp.mvp.views.LoginView

class LoginPresenterImpl: LoginPresenter, AbstractBasePresenter<LoginView>() {

    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl

    override fun onTapLogin(email: String, password: String) {
        mAuthenticationModel.patientLogin(email, password, onSuccess = {
            mView?.navigateToHomeScreen()
        }, onFailure = {
            mView?.showError(it)
        })
    }

    override fun onTapRegister() {
        mView?.navigateToRegistrationScreen()
    }
}