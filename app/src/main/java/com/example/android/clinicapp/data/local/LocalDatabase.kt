package com.example.android.clinicapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android.clinicapp.data.dto.RecordsDTO
import com.example.android.clinicapp.data.dto.DoctorsDTO
import com.example.android.clinicapp.data.dto.PatientsDTO
import com.example.android.clinicapp.data.dto.UserDTO


@Database(entities = [RecordsDTO::class], version = 1, exportSchema = false)
abstract class RecordsDatabase : RoomDatabase() {

    abstract fun recordsDao(): RecordsDao
}

@Database(entities = [PatientsDTO::class], version = 1, exportSchema = false)
abstract class PatientsDatabase : RoomDatabase() {

    abstract fun userPDao(): PatientsDao
}


@Database(entities = [DoctorsDTO::class], version = 1, exportSchema = false)
abstract class DoctorsDatabase : RoomDatabase() {

    abstract fun userDDao(): DoctorsDao
}

@Database(entities = [UserDTO::class], version = 1, exportSchema = false)
abstract class UserDataBase : RoomDatabase() {

    abstract fun userDao(): DoctorsDao
}
