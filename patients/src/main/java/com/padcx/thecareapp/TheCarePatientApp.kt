package com.padcx.thecareapp

import android.app.Application
import com.padcx.happy_food_delivery.data.models.FoodDeliveryModelImpl

class TheCarePatientApp: Application() {

    override fun onCreate() {
        super.onCreate()

        FoodDeliveryModelImpl.initDatabase(applicationContext)
    }
}