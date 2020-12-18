package com.padcx.doctors.mvp.presenters.impls

import com.padcx.doctors.DoctorPrefs
import com.padcx.doctors.mvp.presenters.LoginPresenter
import com.padcx.doctors.mvp.views.LoginView
import com.padcx.shared.data.models.AuthenticationModel
import com.padcx.shared.data.models.AuthenticationModelImpl
import com.padcx.shared.data.models.TheCareAppModel
import com.padcx.shared.data.models.TheCareAppModelImpl
import com.padcx.shared.mvp.presenters.AbstractBasePresenter

class LoginPresenterImpl: LoginPresenter, AbstractBasePresenter<LoginView>() {

    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl
    private val mTheCareModelImpl: TheCareAppModel = TheCareAppModelImpl

    override fun onTapLogin(email: String, password: String) {
        mAuthenticationModel.doctorLogin(email, password, onSuccess = {
            mTheCareModelImpl.getDoctor(email, onSuccess = {
                DoctorPrefs.setUserId(it.id)
                DoctorPrefs.setUserInfo(it)
                mView?.navigateToHomeScreen()
            }, onFailure = {
                mView?.showError(it)
            })
        }, onFailure = {
            mView?.showError(it)
        })

    }

    override fun onTapRegister() {
        mView?.navigateToRegisterScreen()
    }
}