package com.padcx.thecareapp.mvp.presenters

import com.padcx.shared.mvp.presenters.BasePresenter
import com.padcx.thecareapp.mvp.views.RegisterView

interface RegisterPresenter :
    BasePresenter<RegisterView> {

    fun onTapLogin()
    fun onTapRegister(
        email: String, username: String, password: String,
        phone: String, image: String, dob: String, height: String,
        bloodType: String, weight: String, bloodPressure: String,
        allergicMedicine: String
    )
}