package com.padcx.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcx.shared.data.vos.SpecialityVO

@Dao
interface TheCareDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpecialities(specialities: List<SpecialityVO>)

    @Query("SELECT * FROM speciality")
    fun getSpecialities(): LiveData<List<SpecialityVO>>

    @Query("SELECT * FROM speciality WHERE name = :name")
    fun getSpecialityByName(name: String): LiveData<SpecialityVO>

}