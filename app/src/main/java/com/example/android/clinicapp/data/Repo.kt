package com.example.android.clinicapp.data

import androidx.lifecycle.MutableLiveData
import com.example.android.clinicapp.data.dto.DoctorsDTO
import com.example.android.clinicapp.data.dto.FormDTO
import com.example.android.clinicapp.data.local.DoctorsDao
import com.example.android.clinicapp.data.local.PatientsDao
import com.example.android.clinicapp.data.local.RecordsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repo(private val R:Remote, private val doctorDao:DoctorsDao, private val patientsDao: PatientsDao,
           private val recordsDao: RecordsDao, private val formDTO: FormDTO) {
    var doctor = MutableLiveData<DoctorsDTO>()
    suspend fun refreshDoctorProfile(id:String){
        withContext(Dispatchers.IO) {
            val refreshedProfile = R.getDoctorProfile(id)
            doctorDao.saveRecord(DoctorsDTO(refreshedProfile.id!!,refreshedProfile.name,refreshedProfile.gender,refreshedProfile.workingDays,refreshedProfile.email,refreshedProfile.imageURL,refreshedProfile.city,refreshedProfile.telephone,refreshedProfile.address))
        }
    }
    suspend fun getDoctorProfile(id:String):MutableLiveData<DoctorsDTO>{
        withContext(Dispatchers.IO) {
            doctor.value = doctorDao.getProfileById(id)
        }
        return doctor
    }
}