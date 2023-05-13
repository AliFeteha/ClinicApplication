package com.example.android.clinicapp.data.local


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.clinicapp.data.consts.Comment
import com.example.android.clinicapp.data.dto.*
import com.example.android.clinicapp.utils.TypeConverter

@Dao
interface RecordsDao{

        @Query("SELECT * FROM records")
        suspend fun getRecords(): List<RecordsDTO>

        @Query("SELECT * FROM records where patient_id = :patientId")
        suspend fun getRecordsByPatientId(patientId: String): RecordsDTO?

        @Query("SELECT * FROM records where doctor_id = :doctorId")
        suspend fun getRecordsByDoctorId(doctorId: String): RecordsDTO?

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun saveRecord(record: RecordsDTO)

        @Query("DELETE FROM records")
        suspend fun clear()

}

@Dao
interface PatientsDao{

        @Query("SELECT * FROM patients where id = :patientId")
        suspend fun getProfileById(patientId: String): PatientsDTO?

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun saveRecord(patient: PatientsDTO)

        @Query("DELETE FROM patients")
        suspend fun clear()

}

@Dao
interface DoctorsDao{

        @Query("SELECT * FROM doctors where id = :doctorId")
        suspend fun getProfileById(doctorId: String): DoctorsDTO

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun saveRecord(doctor: DoctorsDTO)

        @Query("DELETE FROM doctors")
        suspend fun clear()

}

@Dao
interface FormDao{

        @Query("SELECT * FROM form")
        suspend fun getForm():List<FormDTO>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun saveQuestion(user : FormDTO)

        @Query("UPDATE form SET comments = :comment WHERE id LIKE :id ")
        suspend fun updateComments(id: Int, comment: Comment)

        @Query("DELETE FROM form WHERE id LIKE :id")
        suspend fun delete(id: Int)

        @Query("DELETE FROM form")
        suspend fun clear()

}

@Dao
interface DaysDao{

        @Query("SELECT * FROM days")
        suspend fun getDays():List<DaysDTO>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun saveDays(daysDTO : DaysDTO)

        @Query("DELETE FROM days")
        suspend fun clear()

}



