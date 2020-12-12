package com.padcx.doctors.mvp.presenters

import com.padcx.doctors.mvp.views.RegisterView
import com.padcx.shared.mvp.presenters.BasePresenter

interface RegisterPresenter: BasePresenter<RegisterView> {

    fun onTapRegister(
        name: String, email: String, password: String,
        phone: String, speciality: String, university: String,
        degree: String, doctorSignature: String, image: String
    )

    fun onTapLogin()
}