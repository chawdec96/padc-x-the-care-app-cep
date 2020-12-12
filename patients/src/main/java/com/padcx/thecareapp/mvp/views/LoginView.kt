package com.padcx.thecareapp.mvp.views

import com.padcx.shared.mvp.views.BaseView

interface LoginView: BaseView {

    fun navigateToHomeScreen()
    fun navigateToRegistrationScreen()
}