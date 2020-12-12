package com.padcx.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcx.shared.data.vos.ConsultationVO
import com.padcx.shared.data.vos.QuestionTemplateVO

@Dao
interface DoctorDao {

    @Query("SELECT * FROM questionTemplate")
    fun getQuestionTemplates(): LiveData<List<QuestionTemplateVO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuestionTemplate(questionTemplate: List<QuestionTemplateVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertConsultations(consultations: List<ConsultationVO>)

    @Query("SELECT * FROM consultation")
    fun getConsultations(): LiveData<List<ConsultationVO>>

}