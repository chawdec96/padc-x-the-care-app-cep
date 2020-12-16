package com.padcx.thecareapp

import android.app.Application
import com.padcx.shared.data.models.TheCareAppModelImpl

class TheCarePatientApp: Application() {

    companion object{
        lateinit var INSTANCE: TheCarePatientApp
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