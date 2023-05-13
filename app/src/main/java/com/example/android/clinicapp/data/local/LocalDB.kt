package com.example.android.clinicapp.data.local

import android.content.Context
import androidx.room.Room
import com.example.android.clinicapp.utils.TypeConverter

object LocalDB {

    //first database of the records
    fun createRecordsDao(context: Context): RecordsDao {
        return Room.databaseBuilder(
            context.applicationContext,
            RecordsDatabase::class.java, "Records.db"
        ).build().recordsDao()
    }
    fun createPatientDao(context: Context): PatientsDao {
        return Room.databaseBuilder(
            context.applicationContext,
            PatientsDatabase::class.java, "Patients.db"
        ).build().userPDao()
    }

    fun createDoctorDao(context: Context): DoctorsDao {
        return Room.databaseBuilder(
            context.applicationContext,
            DoctorsDatabase::class.java, "Doctors.db"
        ).build().userDDao()
    }

    fun createFormDao(context: Context): FormDao {
        return Room.databaseBuilder(
            context.applicationContext,
            FormDatabase::class.java, "form.db"
        ).build().formDao()
    }
    fun createDaysDao(context: Context): DaysDao {
        return Room.databaseBuilder(
            context.applicationContext,
            DaysDb::class.java, "days.db"
        ).build().DaysDao()
    }


}