package com.example.android.clinicapp.data

import androidx.lifecycle.LiveData
import com.example.android.clinicapp.data.dto.DoctorsDTO
import com.example.android.clinicapp.data.dto.FormDTO
import com.example.android.clinicapp.data.local.DoctorsDao
import com.example.android.clinicapp.data.local.DoctorsDatabase
import com.example.android.clinicapp.data.local.PatientsDao
import com.example.android.clinicapp.data.local.RecordsDao
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repo(private val R:remote, private val doctorDao:DoctorsDao,private val patientsDao: PatientsDao,
           private val recordsDao: RecordsDao, private val formDTO: FormDTO,docId:String) {
    val doctor: LiveData<DoctorsDTO> = doctorDao.getProfileById(docId)
    suspend fun refreshDoctorProfile(id:String){
        withContext(Dispatchers.IO) {
            val doctor = R.getDoctorProfile(id)
            doctorDao.saveRecord(DoctorsDTO(doctor.id,doctor.name,doctor.gender,doctor.workingDays,doctor.email,doctor.imageURL,doctor.city,doctor.telephone,doctor.address))
        }
    }
}