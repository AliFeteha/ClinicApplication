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
import com.example.android.clinicapp.utils.TypeConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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

    //local db gets refresh by remote
    suspend fun refreshDoctorProfile(id:String){
        withContext(Dispatchers.Unconfined) {
            val refreshedProfile = remote.getDoctorProfile(id)
            doctorDao.saveRecord(TypeConverter().doctorToDoctorDTO(refreshedProfile))
        }
    }
    //remote connection only
    suspend fun getDoctorProfile(id:String){
        withContext(Dispatchers.Unconfined) {
            doctor.value = doctorDao.getProfileById(id)
        }
    }
    //local db gets refresh by remote
    suspend fun refreshPatientProfile(id:String){
        withContext(Dispatchers.Unconfined) {
            val refreshedProfile = remote.getPatientProfile(id)
            patientsDao.saveRecord(TypeConverter().patientToPatientDto(refreshedProfile))
        }
    }
    //remote connection only
    suspend fun getPatientProfile(id:String){
        withContext(Dispatchers.Unconfined) {
            patient.value = patientsDao.getProfileById(id)
        }
    }
    //signs up user online and write it on dataBase and preference
    suspend fun signUpPatient(patient: Patient,password:String){
        withContext(Dispatchers.Unconfined) {
            remote.signUpPatient(patient,password)
            patientsDao.saveRecord(PatientsDTO(patient.id!!,patient.name,patient.gender,patient.email,patient.birthDate,patient.imageUrl,patient.address,patient.city,patient.mobilePhone,patient.bloodType,patient.medicalIssues,patient.emergencyContact,patient.insurance))
            patientsDao.saveRecord(TypeConverter().patientToPatientDto(patient))
            PreferenceControl().write(patient)
        }
    }
    suspend fun signUpDoctor(doctor: Doctor, password:String){
        withContext(Dispatchers.Unconfined) {
            remote.signUpDoctor(doctor,password)
            doctorDao.saveRecord(TypeConverter().doctorToDoctorDTO(doctor))
            PreferenceControl().write(doctor)
        }
    }



    //Remote Connections
    fun verifyEmailExists(email :String): Boolean{
        var thing = FirebaseControl()
        runBlocking {
            launch(Dispatchers.Unconfined) {
                thing = remote.fireBaseAuthentication(email)
            }
        }
        if (thing.password == null)
            return false
        return true

    }
    //fn to write on the preference if authenticated successfully
    suspend fun authenticate(email:String, password:String):Boolean{
        val thing: FirebaseControl
        withContext(Dispatchers.Unconfined) {
            thing = remote.fireBaseAuthentication(email)
        }
        if (thing.email == email && thing.password == password) {
            PreferenceControl().writeId(thing.id!!)
            return true
        }
        return false
    }

    //check profile - get profile and write it on preference and liveData
    suspend fun loginAuth() {
        val id = PreferenceControl().readId()
        withContext(Dispatchers.Unconfined) {
            val type = checkProfile(id)
            if (type == Type.Patient)
                id?.let { getPatientProfile(it) }
            else
                id?.let { getDoctorProfile(it) }
        }
    }
    //controls the flow of the registration
    suspend fun registerAuth(type: Type ,password :String) {
        if (type == Type.Patient)
            signUpPatient(PreferenceControl().readPatient(), password)
        else
            signUpDoctor(PreferenceControl().readDoctor(), password)
        return
    }
    private suspend fun checkProfile(id: String?):Type {
        val profile = remote.getDoctorProfile(id!!)
        return if (profile.id == null)
            Type.Doctor
        else
            Type.Patient
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