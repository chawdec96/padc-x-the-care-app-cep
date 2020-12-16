package com.padcx.shared.data.models

import com.padcx.shared.network.auth.AuthManager

interface AuthenticationModel {

    var mAuthManager: AuthManager

    // Patient Authentication
    fun patientLogin(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun patientRegister(email: String, password: String, username: String, phone: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    // Doctor Authentication
    fun doctorLogin(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun doctorRegistration(email: String, password: String, username: String, phone: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun getUserEmail(): String
}