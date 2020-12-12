package com.padcx.thecareapp.mvp.presenters.impls

import com.padcx.shared.data.models.AuthenticationModel
import com.padcx.shared.data.models.AuthenticationModelImpl
import com.padcx.shared.mvp.presenters.AbstractBasePresenter
import com.padcx.shared.network.impls.FirebaseCloudFirestoreApiImpl
import com.padcx.thecareapp.mvp.presenters.RegisterPresenter
import com.padcx.thecareapp.mvp.views.RegisterView

class RegisterPresenterImpl: RegisterPresenter, AbstractBasePresenter<RegisterView>() {

    private val mAuthenticationModelImpl: AuthenticationModel = AuthenticationModelImpl
    private val mFirebaseCloudFirebaseApi = FirebaseCloudFirestoreApiImpl

    override fun onTapLogin() {
        mView?.navigateToLoginScreen()
    }

    override fun onTapRegister(
        email: String,
        username: String,
        password: String,
        phone: String,
        image: String
    ) {
        mAuthenticationModelImpl.patientLogin(email, password, onSuccess = {
            mView?.navigateToLoginScreen()
            mFirebaseCloudFirebaseApi.registeredPatient(email, password, username, phone, image)
        }, onFailure = {
            mView?.showError(it)
        })
    }
}