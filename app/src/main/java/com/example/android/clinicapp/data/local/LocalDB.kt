package com.example.android.clinicapp.data.local

import android.content.Context
import androidx.room.Room

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

    fun createUserDao(context: Context): DoctorsDao {
        return Room.databaseBuilder(
            context.applicationContext,
            UserDataBase::class.java, "User.db"
        ).build().userDao()
    }


}