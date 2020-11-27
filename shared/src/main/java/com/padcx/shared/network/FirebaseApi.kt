package com.padcx.shared.network

interface FirebaseApi {

    fun addRegisteredPatient(
        email: String, password: String, username: String, phone: String, image: String
    )

    fun addRegisteredDoctor(
        email: String, password: String,
        username: String, phone: String,
        image: String, speciality: String,
        experience: Int, doctorSignature: String,
        degree: String, university: String
    )
}