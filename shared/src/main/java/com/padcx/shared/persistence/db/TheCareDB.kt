package com.padcx.shared.persistence.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.padcx.shared.data.vos.*
import com.padcx.shared.persistence.daos.DoctorDao
import com.padcx.shared.persistence.daos.PatientDao
import com.padcx.shared.persistence.daos.TheCareDao
import com.padcx.shared.persistence.typeconverters.*

@Database(entities = [CheckOutVO::class, SpecialityVO::class, QuestionTemplateVO::class, DoctorVO::class, ConsultationVO::class], version = 2, exportSchema = false)
@TypeConverters(PrescriptionTypeConverter::class, ChatTypeConverter::class, MedicineTypeConverter::class, QuestionTemplateTypeConverter::class,
CaseSummaryTypeConverter::class, DoctorTypeConverter::class, PatientTypeConverter::class, RoutineTypeConverter::class, DeliveryRoutineTypeConverter::class, SpecificQuestionTypeConverter::class,
GeneralQuestionsTypeConverter::class)
abstract class TheCareDB: RoomDatabase() {

    companion object{
        private const val DB_NAME = "TheCare.db"

        private var dbInstance: TheCareDB ?= null

        fun getDBInstance(context: Context): TheCareDB{
            when(dbInstance){
                null -> {
                    dbInstance = Room.databaseBuilder(context, TheCareDB::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            val i = dbInstance
            return i!!
        }
    }

    abstract fun patientDao(): PatientDao

    abstract fun doctorDao(): DoctorDao

    abstract fun theCareDao(): TheCareDao

}