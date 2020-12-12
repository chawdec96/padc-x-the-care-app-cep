package com.padcx.happy_food_delivery

import android.app.Application
import com.padcx.happy_food_delivery.data.models.FoodDeliveryModelImpl

class RestaurantsApp: Application() {

    override fun onCreate() {
        super.onCreate()

        FoodDeliveryModelImpl.initDatabase(applicationContext)
    }
}