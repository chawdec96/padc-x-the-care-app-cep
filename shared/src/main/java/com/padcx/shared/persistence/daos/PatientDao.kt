package com.padcx.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcx.shared.data.vos.CheckOutVO
import com.padcx.shared.data.vos.ConsultationVO
import com.padcx.shared.data.vos.DoctorVO

@Dao
interface PatientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDoctors(doctors: List<DoctorVO>)

    @Query("SELECT * FROM doctor WHERE speciality = :speciality")
    fun getDoctorsBySpeciality(speciality: String): LiveData<List<DoctorVO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertConsultations(consultations: List<ConsultationVO>)

    @Query("SELECT * FROM consultation")
    fun getConsultations(): LiveData<List<ConsultationVO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCheckOuts(checkOuts: List<CheckOutVO>)

    @Query("SELECT * FROM checkOut")
    fun getCheckOuts(): LiveData<List<CheckOutVO>>

}