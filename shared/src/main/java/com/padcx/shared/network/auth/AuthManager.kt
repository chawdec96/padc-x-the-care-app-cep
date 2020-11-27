package com.padcx.shared.network.auth

interface AuthManager {

    // patient authentication
    fun patientLogin(email: String, password: String, onSuccess:() -> Unit, onFailure: (String) -> Unit)
    fun patientRegister(email: String, password: String, name: String, phone: String,onSuccess: () -> Unit, onFailure: (String) -> Unit)

    // doctor authentication
    fun doctorLogin(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)
    fun doctorRegister(email: String, password: String, name: String, phone: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)

}