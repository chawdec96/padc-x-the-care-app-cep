package com.padcx.shared.network.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

object FirebaseAuthManagerImpl: AuthManager {

    private val mFirebaseAuth = FirebaseAuth.getInstance()

    override fun patientLogin(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {task ->
            if (task.isSuccessful){
                onSuccess()
            }else{
                onFailure(task.exception?.message ?: "Please Check Internet Connection")
            }
        }
    }

    override fun patientRegister(
        email: String,
        password: String,
        name: String,
        phone: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful && task.isComplete){
                mFirebaseAuth.currentUser?.updateProfile(
                    UserProfileChangeRequest.Builder()
                        .setDisplayName(name)
                        .build()
                )
                onSuccess()
            }else{
                onFailure(task.exception?.message ?: "Check Your Internet Connection")
            }
        }
    }

    override fun doctorLogin(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful){
                onSuccess()
            }else{
                onFailure(it.exception?.message ?: "Please Check Your Internet Connection")
            }
        }
    }

    override fun doctorRegister(
        email: String,
        password: String,
        name: String,
        phone: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {task ->
            if (task.isSuccessful && task.isComplete){
                mFirebaseAuth.currentUser?.updateProfile(
                    UserProfileChangeRequest.Builder()
                        .setDisplayName(name)
                        .build()
                )
                onSuccess()
            }else{
                onFailure(task.exception?.message ?: "Please Check Your Internet Connection")
            }
        }
    }

    override fun getUserEmail(): String {
        return mFirebaseAuth.currentUser?.email ?: ""
    }
}