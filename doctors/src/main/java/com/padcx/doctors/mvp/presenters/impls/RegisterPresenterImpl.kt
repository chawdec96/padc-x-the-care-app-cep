package com.padcx.doctors.mvp.presenters.impls

import com.padcx.doctors.mvp.presenters.RegisterPresenter
import com.padcx.doctors.mvp.views.RegisterView
import com.padcx.shared.mvp.presenters.AbstractBasePresenter

class RegisterPresenterImpl: RegisterPresenter, AbstractBasePresenter<RegisterView>() {
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
        TODO("Not yet implemented")
    }

    override fun onTapLogin() {
        TODO("Not yet implemented")
    }
}