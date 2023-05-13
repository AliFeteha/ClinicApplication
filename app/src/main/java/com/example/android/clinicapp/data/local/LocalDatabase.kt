package com.example.android.clinicapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.android.clinicapp.data.dto.*


@Database(entities = [RecordsDTO::class], version = 1, exportSchema = false)
@TypeConverters(com.example.android.clinicapp.utils.TypeConverter::class)
abstract class RecordsDatabase : RoomDatabase() {

    abstract fun recordsDao(): RecordsDao
}

@Database(entities = [PatientsDTO::class], version = 1, exportSchema = false)
@TypeConverters(com.example.android.clinicapp.utils.TypeConverter::class)
abstract class PatientsDatabase : RoomDatabase() {

    abstract fun userPDao(): PatientsDao
}


@Database(entities = [DoctorsDTO::class], version = 1, exportSchema = false)
@TypeConverters(com.example.android.clinicapp.utils.TypeConverter::class)
abstract class DoctorsDatabase : RoomDatabase() {

    abstract fun userDDao(): DoctorsDao
}

@Database(entities = [FormDTO::class], version = 1, exportSchema = false)
@TypeConverters(com.example.android.clinicapp.utils.TypeConverter::class)
abstract class FormDatabase : RoomDatabase() {

    abstract fun formDao(): FormDao
}

@Database(entities = [DaysDTO::class], version = 1, exportSchema = false)
@TypeConverters(com.example.android.clinicapp.utils.TypeConverter::class)
abstract class DaysDb : RoomDatabase() {

    abstract fun DaysDao(): DaysDao
}
