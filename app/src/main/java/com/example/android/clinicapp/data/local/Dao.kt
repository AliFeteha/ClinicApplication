package com.example.android.clinicapp.data.local


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.android.clinicapp.data.consts.Comment
import com.example.android.clinicapp.data.dto.*
import com.example.android.clinicapp.utils.TypeConverter

@Dao
interface RecordsDao{

        @Query("SELECT * FROM records")
        suspend fun getRecords(): List<RecordsDTO>

        @Query("SELECT * FROM records where patient_id = :patientId")
        suspend fun getRecordsByPatientId(patientId: String): List<RecordsDTO>?

        @Query("SELECT * FROM records where doctor_id = :doctorId")
        suspend fun getRecordsByDoctorId(doctorId: String): List<RecordsDTO>?

        @Transaction
        suspend fun saveRecords(records : List<RecordsDTO>) {
                records.forEach { saveRecord(it)}
        }

        @Insert
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

        @Query("UPDATE form SET comments = :comments WHERE id LIKE :id ")
        suspend fun updateComments(id: String, comments: List<Comment>)

        @Query("SELECT comments FROM form WHERE id = :entityId")
        suspend fun getStringField(entityId: String): String?

        @Transaction
        suspend fun appendComment(entityId: String, comment: Comment) {
                val existingString = getStringField(entityId) ?: ""
                val list:MutableList<Comment> = TypeConverter().stringToCommentList(existingString) as MutableList<Comment>
                list.add(comment)
                updateComments(entityId, list)
        }

        @Query("DELETE FROM form WHERE id LIKE :id")
        suspend fun delete(id: String)

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



