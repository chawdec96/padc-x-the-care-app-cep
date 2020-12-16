package com.padcx.thecareapp.mvp.presenters.impls

import com.padcx.shared.data.models.AuthenticationModel
import com.padcx.shared.data.models.AuthenticationModelImpl
import com.padcx.shared.data.models.TheCareAppModel
import com.padcx.shared.data.models.TheCareAppModelImpl
import com.padcx.shared.mvp.presenters.AbstractBasePresenter
import com.padcx.thecareapp.mvp.presenters.LoginPresenter
import com.padcx.thecareapp.mvp.views.LoginView
import com.padcx.thecareapp.persistence.PatientPrefs

class LoginPresenterImpl: LoginPresenter, AbstractBasePresenter<LoginView>() {

    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl
    private val mTheCareModelImpl: TheCareAppModel = TheCareAppModelImpl

    override fun onTapLogin(email: String, password: String) {
        mAuthenticationModel.patientLogin(email, password, onSuccess = {
            mTheCareModelImpl.getPatient(email, onSuccess = {
                PatientPrefs.setUserId(it.id)
                PatientPrefs.setUserInfo(it)
                mView?.navigateToHomeScreen()
            }, onFailure = {
                mView?.showError(it)
            })

        }, onFailure = {
            mView?.showError(it)
        })
    }

    override fun onTapRegister() {
        mView?.navigateToRegistrationScreen()
    }
}