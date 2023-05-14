package com.example.android.clinicapp.data

import android.content.Context
import android.text.BoringLayout
import androidx.lifecycle.MutableLiveData
import com.example.android.clinicapp.data.consts.*
import com.example.android.clinicapp.data.dto.DoctorsDTO
import com.example.android.clinicapp.data.dto.PatientsDTO
import com.example.android.clinicapp.data.dto.RecordsDTO
import com.example.android.clinicapp.data.local.*
import com.example.android.clinicapp.utils.PreferenceControl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repo(context: Context) {
    private val remote = Remote()
    private val doctorDao:DoctorsDao = LocalDB.createDoctorDao(context = context)
    private val patientsDao: PatientsDao = LocalDB.createPatientDao(context = context)
    private val recordsDao: RecordsDao = LocalDB.createRecordsDao(context = context)
    private val formDao: FormDao = LocalDB.createFormDao(context = context)
    private val days:DaysDao = LocalDB.createDaysDao(context = context)

    var doctor = MutableLiveData<DoctorsDTO>()
    var patient = MutableLiveData<PatientsDTO>()
    var appointments = MutableLiveData<List<RecordsDTO>>()

    suspend fun refreshDoctorProfile(id:String){
        withContext(Dispatchers.IO) {
            val refreshedProfile = remote.getDoctorProfile(id)
            doctorDao.saveRecord(DoctorsDTO(refreshedProfile.id!!,refreshedProfile.name,refreshedProfile.gender,refreshedProfile.workingDays,refreshedProfile.email,refreshedProfile.imageURL,refreshedProfile.city,refreshedProfile.telephone,refreshedProfile.address))
        }
    }
    suspend fun getDoctorProfile(id:String):MutableLiveData<DoctorsDTO>{
        withContext(Dispatchers.IO) {
            doctor.value = doctorDao.getProfileById(id)
        }
        return doctor
    }
    suspend fun refreshPatientProfile(id:String){
        withContext(Dispatchers.IO) {
            val refreshedProfile = remote.getPatientProfile(id)
            patientsDao.saveRecord(PatientsDTO(refreshedProfile.id!!,refreshedProfile.name,refreshedProfile.gender,refreshedProfile.email,refreshedProfile.birthDate,refreshedProfile.imageUrl,refreshedProfile.id,refreshedProfile.city,refreshedProfile.mobilePhone,refreshedProfile.bloodType,refreshedProfile.medicalIssues,refreshedProfile.emergencyContact,refreshedProfile.insurance))
        }
    }
    suspend fun getPatientProfile(id:String):MutableLiveData<PatientsDTO>{
        withContext(Dispatchers.IO) {
            patient.value = patientsDao.getProfileById(id)
        }
        return patient
    }
    suspend fun signUpPatient(patient: Patient,password:String){
        withContext(Dispatchers.IO) {
            remote.signUpPatient(patient,password)
            patientsDao.saveRecord(PatientsDTO(patient.id!!,patient.name,patient.gender,patient.email,patient.birthDate,patient.imageUrl,patient.address,patient.city,patient.mobilePhone,patient.bloodType,patient.medicalIssues,patient.emergencyContact,patient.insurance))
        }
    }
    suspend fun signUpDoctor(doctor: Doctor, password:String){
        withContext(Dispatchers.IO) {
            remote.signUpDoctor(doctor,password)
            doctorDao.saveRecord(DoctorsDTO(doctor.id!!,doctor.name,doctor.gender,doctor.workingDays,doctor.email,doctor.imageURL,doctor.city,doctor.telephone,doctor.address))
        }
    }



    //Remote Connections
    suspend fun verifyEmailExists(email :String): Boolean{
        val thing:FirebaseControl
        withContext(Dispatchers.IO) {
            thing = remote.fireBaseAuthentication(email)
        }
        if (thing.password == null)
            return false
        return true
    }
    //fn to write on the preference if authenticated successfully
    suspend fun authenticate(email:String, password:String):Boolean{
        val thing: FirebaseControl
        withContext(Dispatchers.IO) {
            thing = remote.fireBaseAuthentication(email)
        }
        if (thing.email == email && thing.password == password) {
            PreferenceControl().writeId(thing.id!!)
            return true
        }
        return false
    }
    //check profile - todo get profile and write it on preference and liveData
    suspend fun loginAuth() {
        val id = PreferenceControl().readId()
        withContext(Dispatchers.IO) {
            checkProfile(id)
            //Todo
        }

        suspend fun registerAuth(type: Type, id: String) {
            withContext(Dispatchers.IO) {
                //todo
            }
            return
        }
    }
    private fun checkProfile(id: String?) {
        TODO("Not yet implemented")
    }
    private fun appointmentsToRecords(it:List<Appointment>):List<RecordsDTO>{
        val records : MutableList<RecordsDTO> = mutableListOf()
        for (appointment in it){
            records.add(RecordsDTO(appointment.title,appointment.pName,appointment.pId,appointment.dName,appointment.date,appointment.dId,appointment.id))
        }
        return records
    }
    suspend fun addAppointment(appointment:Appointment){
        withContext(Dispatchers.IO) {
            remote.addAppointment(appointment)
            recordsDao.saveRecord(RecordsDTO(appointment.title,appointment.pName,appointment.pId,appointment.dName,appointment.date,appointment.dId,appointment.id))
        }
    }
    suspend fun refreshAllAppointments(){
        withContext(Dispatchers.IO) {
            val refreshedAppointments = remote.getAllAppointments()
            recordsDao.saveRecords(appointmentsToRecords(refreshedAppointments))
        }
    }
    suspend fun getPatientRecords(id:String):MutableLiveData<List<RecordsDTO>>{
        withContext(Dispatchers.IO) {
            appointments.value = recordsDao.getRecordsByPatientId(id)
        }
        return appointments
    }
    suspend fun getDoctorRecords(id:String):MutableLiveData<List<RecordsDTO>>{
        withContext(Dispatchers.IO) {
            appointments.value = recordsDao.getRecordsByDoctorId(id)
        }
        return appointments
    }

}