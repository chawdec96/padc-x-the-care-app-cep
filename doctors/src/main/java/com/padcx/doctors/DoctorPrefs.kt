package com.padcx.doctors

import android.content.Context
import com.google.gson.Gson
import com.padcx.shared.data.vos.DoctorVO

object DoctorPrefs {

    private const val DOCTOR_SHARED_PREFS = "PATIENT_SHARED_PREFS"
    private const val SHARED_PREFS_USER_ID = "SHARED_PREFS_USER_ID"
    private const val SHARED_PREFS_USER_INFO = "SHARED_PREFS_USER_INFO"

    private fun sharedPrefs() =
        TheCareDoctorApp.INSTANCE.getSharedPreferences(DOCTOR_SHARED_PREFS, Context.MODE_PRIVATE)

    private val editor = sharedPrefs().edit()

    fun setUserId(id: String){
        editor.putString(SHARED_PREFS_USER_ID, id)
            .apply()
    }

    fun getUserId() =
        sharedPrefs().getString(SHARED_PREFS_USER_ID, "")

    fun getUserInfo(): DoctorVO{
        val user = sharedPrefs().getString(SHARED_PREFS_USER_INFO, null)
        return Gson().fromJson(user, DoctorVO::class.java)
    }

    fun setUserInfo(user: DoctorVO){
        editor.putString(SHARED_PREFS_USER_INFO, Gson().toJson(user))
            .apply()
    }

}