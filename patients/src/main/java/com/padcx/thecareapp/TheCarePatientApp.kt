package com.padcx.thecareapp

import android.app.Application
import com.padcx.shared.data.models.TheCareAppModelImpl

class TheCarePatientApp: Application() {

    override fun onCreate() {
        super.onCreate()

        TheCareAppModelImpl.initDatabase(applicationContext)
    }
}