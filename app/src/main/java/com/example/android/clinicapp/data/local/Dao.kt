package com.example.android.clinicapp.data.local


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.clinicapp.data.consts.Doctor
import com.example.android.clinicapp.data.consts.Patient
import com.example.android.clinicapp.data.consts.Record
import com.example.android.clinicapp.data.dto.DoctorsDTO
import com.example.android.clinicapp.data.dto.PatientsDTO
import com.example.android.clinicapp.data.dto.RecordsDTO
import com.example.android.clinicapp.data.dto.UserDTO

@Dao
interface RecordsDao{

        @Query("SELECT * FROM records")
        suspend fun getRecords(): List<Record>

        @Query("SELECT * FROM records where patient_id = :patientId")
        suspend fun getRecordsByPatientId(patientId: Int): Record?

        @Query("SELECT * FROM records where doctor_id = :doctorId")
        suspend fun getRecordsByDoctorId(patientId: Int): Record?

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun saveRecord(record: RecordsDTO)

        @Query("DELETE FROM records")
        suspend fun clear()

}

@Dao
interface PatientsDao{

        @Query("SELECT * FROM patients where id = :patientId")
        suspend fun getProfileById(patientId: Int): Patient?

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun saveRecord(patient: PatientsDTO)

        @Query("DELETE FROM patients")
        suspend fun clear()

}

@Dao
interface DoctorsDao{

        @Query("SELECT * FROM doctors where id = :doctorId")
        suspend fun getProfileById(doctorId: Int): Patient?

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun saveRecord(doctor: DoctorsDTO)

        @Query("DELETE FROM doctors")
        suspend fun clear()

}

@Dao
interface UserDao{

        @Query("SELECT * FROM user ORDER BY ROWID ASC LIMIT 1")
        suspend fun getProfile():Doctor

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun saveLog(user : UserDTO)

        @Query("DELETE FROM user")
        suspend fun clear()

}


