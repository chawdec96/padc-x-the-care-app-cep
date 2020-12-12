package com.padcx.shared.data.models

import android.content.Context
import com.padcx.shared.persistence.db.TheCareDB

abstract class BaseModel {

    protected lateinit var mTheCareDB: TheCareDB

    fun initDatabase(context: Context){
        mTheCareDB = TheCareDB.getDBInstance(context)
    }

}