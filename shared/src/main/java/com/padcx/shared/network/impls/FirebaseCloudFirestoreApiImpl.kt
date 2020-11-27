package com.padcx.shared.network.impls

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.padcx.shared.network.FirebaseApi

object FirebaseCloudFirestoreApiImpl: FirebaseApi {

    val db = Firebase.firestore

    override fun addRegisteredPatient(
        email: String,
        password: String,
        username: String,
        phone: String,
        image: String
    ) {
        val patientMap = hashMapOf(
            "name" to username,
            "email" to email,
            "phone" to phone,
            "image" to image
        )

        db.collection("patients")
            .document(email)
            .set(patientMap)
            .addOnSuccessListener { Log.d("Success", "Successfully added a patient") }
            .addOnFailureListener { Log.d("Failure", "Failed to add a patient") }

    }

    override fun addRegisteredDoctor(
        email: String,
        password: String,
        username: String,
        phone: String,
        image: String,
        speciality: String,
        experience: Int,
        doctorSignature: String,
        degree: String,
        university: String
    ) {
        val doctorMap = hashMapOf(
            "name" to username,
            "email" to email,
            "phone" to phone,
            "image" to image,
            "speciality" to speciality,
            "experience" to experience,
            "doctorSignature" to doctorSignature,
            "degree" to degree,
            "university" to university
        )

        db.collection("doctors")
            .document(email)
            .set(doctorMap)
            .addOnSuccessListener { Log.d("Success", "Successfully added a doctor") }
            .addOnFailureListener { Log.d("Failure", "Failed to add a doctor") }
    }
}