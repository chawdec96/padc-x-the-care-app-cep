package com.padcx.thecareapp.mvp.views

import com.padcx.shared.BaseView

interface LoginView: BaseView {

    fun navigateToHomeScreen()
    fun navigateToRegistrationScreen()
}