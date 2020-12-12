package com.padcx.doctors

import android.app.Application
import com.padcx.shared.data.models.TheCareAppModelImpl

class TheCareDoctorApp: Application() {

    override fun onCreate() {
        super.onCreate()

        TheCareAppModelImpl.initDatabase(applicationContext)
    }
}