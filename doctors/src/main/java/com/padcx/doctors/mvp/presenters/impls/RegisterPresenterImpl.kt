package com.padcx.doctors.mvp.presenters.impls

import com.padcx.doctors.mvp.presenters.RegisterPresenter
import com.padcx.doctors.mvp.views.RegisterView
import com.padcx.shared.data.models.AuthenticationModel
import com.padcx.shared.data.models.AuthenticationModelImpl
import com.padcx.shared.mvp.presenters.AbstractBasePresenter
import com.padcx.shared.network.impls.FirebaseCloudFirestoreApiImpl

class RegisterPresenterImpl: RegisterPresenter, AbstractBasePresenter<RegisterView>() {

    private val mAuthenticationModelImpl: AuthenticationModel = AuthenticationModelImpl
    private val mFirebaseCloudFirebaseApi = FirebaseCloudFirestoreApiImpl

    override fun onTapRegister(
        name: String,
        email: String,
        password: String,
        phone: String,
        speciality: String,
        university: String,
        degree: String,
        doctorSignature: String,
        image: String
    ) {
        mAuthenticationModelImpl.doctorRegistration(email, password, name, phone,onSuccess = {
            mFirebaseCloudFirebaseApi.registeredDoctor(
                email, password, name, phone, image, speciality,
                3, doctorSignature, degree, university
            )
            mView?.navigateToLoginScreen()
        }, onFailure = {
            mView?.showError(it)
        })

    }

    override fun onTapLogin() {
        mView?.navigateToLoginScreen()
    }
}