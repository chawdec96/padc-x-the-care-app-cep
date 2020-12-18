package com.padcx.doctors

import android.app.Application
import com.padcx.shared.data.models.TheCareAppModelImpl

class TheCareDoctorApp: Application() {

    companion object{
        lateinit var INSTANCE: TheCareDoctorApp
    }

    init {
        INSTANCE = this
    }

    override fun onCreate() {
        super.onCreate()

        INSTANCE = this
        TheCareAppModelImpl.initDatabase(applicationContext)
    }
}