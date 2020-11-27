package com.padcx.shared.data.models

import com.padcx.shared.network.auth.AuthManager
import com.padcx.shared.network.auth.FirebaseAuthManagerImpl

object AuthenticationModelImpl: AuthenticationModel {
    override var mAuthManager: AuthManager = FirebaseAuthManagerImpl

    override fun patientLogin(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.patientLogin(email, password, onSuccess, onFailure)
    }

    override fun patientRegister(
        email: String,
        password: String,
        username: String,
        phone: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.patientRegister(email, password, username, phone, onSuccess, onFailure)
    }

    override fun doctorLogin(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.doctorLogin(email, password, onSuccess, onFailure)
    }

    override fun doctorRegistration(
        email: String,
        password: String,
        username: String,
        phone: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.doctorRegister(email, password, username, phone, onSuccess, onFailure)
    }
}