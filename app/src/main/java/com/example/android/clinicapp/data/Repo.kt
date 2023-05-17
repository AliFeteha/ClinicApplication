package com.example.android.clinicapp.data

import android.content.Context
import android.text.BoringLayout
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.example.android.clinicapp.data.consts.*
import com.example.android.clinicapp.data.dto.DoctorsDTO
import com.example.android.clinicapp.data.dto.PatientsDTO
import com.example.android.clinicapp.data.dto.RecordsDTO
import com.example.android.clinicapp.data.local.*
import com.example.android.clinicapp.utils.PreferenceControl
import com.example.android.clinicapp.utils.TypeConverter
import com.google.firebase.database.core.Tag
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.internal.synchronized
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class Repo(context: Context) {
    private val doctorDao:DoctorsDao = LocalDB.createDoctorDao(context = context)
    private val patientsDao: PatientsDao = LocalDB.createPatientDao(context = context)
    val recordsDao: RecordsDao = LocalDB.createRecordsDao(context = context)
    private val formDao: FormDao = LocalDB.createFormDao(context = context)
    private val days:DaysDao = LocalDB.createDaysDao(context = context)

    private val remote = Remote()
    private val context = context
    var doctor = MutableLiveData<DoctorsDTO>()
    var patient = MutableLiveData<PatientsDTO>()
    var appointments = MutableLiveData<List<Appointment>>()

    //local db gets refresh by remote
   /* suspend fun refreshDoctorProfile(id:String){
        withContext(Dispatchers.Unconfined) {

            val refreshedProfile = remote.getDoctorProfile(id)
//            doctorDao.saveRecord(TypeConverter().doctorToDoctorDTO(refreshedProfile))
        }
    }*/
    //remote connection only
    suspend fun getDoctorProfile(id:String){
        withContext(Dispatchers.Unconfined) {
            doctor.value = doctorDao.getProfileById(id)
        }
    }
    //local db gets refresh by remote
   /* suspend fun refreshPatientProfile(id:String){
        withContext(Dispatchers.Unconfined) {
            val refreshedProfile = remote.getPatientProfile(id)
//            patientsDao.saveRecord(TypeConverter().patientToPatientDto(refreshedProfile))
        }
    }*/
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
            PreferenceControl(context).write(patient)
        }
    }
    suspend fun signUpDoctor(doctor: Doctor, password:String){
        withContext(Dispatchers.Unconfined) {
            remote.signUpDoctor(doctor,password)
            doctorDao.saveRecord(TypeConverter().doctorToDoctorDTO(doctor))
            PreferenceControl(context).write(doctor)
        }
    }



    //Remote Connections
    fun verifyEmailExists(firebaseControl:MutableLiveData<FirebaseControl>,email :String){
        remote.fireBaseAuthentication(firebaseControl,email)
    }



    //fn to write on the preference if authenticated successfully
//    fun authenticate(email:String, password:String):Boolean{
//        remote.fireBaseAuthentication(email)
//        //
//        if (thing.email == email && thing.password == password) {
//            PreferenceControl(context = ).writeId(thing.id!!)
//            return true
//        }
//        return false
//    }

    //check profile - get profile and write it on preference and liveData
//    suspend fun loginAuth() {
//        val id = PreferenceControl(context).readId()
//        withContext(Dispatchers.Unconfined) {
//            val type = checkProfile(id)
//            if (type == Type.Patient)
//                id?.let { getPatientProfile(it) }
//            else
//                id?.let { getDoctorProfile(it) }
//     todo somehting is wrong with the logic
//        }
//    }
    //controls the flow of the registration
    suspend fun registerAuth(type: Type ,password :String) {
        if (type == Type.Patient)
            signUpPatient(PreferenceControl(context).readPatient(), password)
        else
            signUpDoctor(PreferenceControl(context).readDoctor(), password)
        return
    }
//    private fun checkProfile(id: String?):Type {
//        //Todo get the things
//        val profile = remote.getDoctorProfile(id!!)
//        return if (profile.id == null)
//            Type.Doctor
//        else
//            Type.Patient
//    }
    private fun appointmentsToRecords(it:List<Appointment>):List<RecordsDTO>{
        val records : MutableList<RecordsDTO> = mutableListOf()
        for (appointment in it){
            records.add(RecordsDTO(appointment.title,appointment.pName,appointment.pId,appointment.dName,appointment.date,appointment.dId,appointment.id))
        }
        return records
    }
    private fun doctorToDoctorDto(it:List<Doctor>):List<DoctorsDTO>{
        val records : MutableList<DoctorsDTO> = mutableListOf()
        for (doctor in it){
            records.add(DoctorsDTO(doctor.id!!,doctor.name,doctor.gender, doctor.workingDays!!,doctor.email,doctor.imageURL,doctor.city,doctor.telephone,doctor.address))
        }
        return records
    }
    suspend fun addAppointment(appointment:Appointment){
        Log.i(" alooo from add appointment",appointment.toString())
        withContext(Dispatchers.Unconfined) {
            remote.addAppointment(appointment)
            recordsDao.saveRecord(RecordsDTO(appointment.title,appointment.pName,appointment.pId,appointment.dName,appointment.date,appointment.dId,appointment.id))
        }
    }
    fun refreshAllDoctors(doctor:MutableLiveData<List<Doctor>>){
        remote.getAllDoctors(doctor)
    }
    suspend fun refreshDoctorsDataBaseFromRemote(Doctor:List<Doctor>){
        doctorDao.saveRecords(doctorToDoctorDto(Doctor))
    }
    suspend fun getDoctors(doctors:MutableLiveData<List<Doctor>>,date:Days) {

        val getDoctors = mutableListOf<Doctor>()
        val doctor = doctorDao.getProfileByDays(listOf(date))
        Log.i(" alooo from getDoctors",doctor.toString())
        for (app in doctor) {
                getDoctors.add(
                    Doctor(
                        app.address,
                        app.city,
                        app.email,
                        app.gender,
                        app.id,
                        app.img_url,
                        app.name,
                        app.telephone,
                        app.workDays
                    )
                ) }

        doctors.value = getDoctors
    }
    fun refreshAllAppointments(case:MutableLiveData<List<Appointment>>){
             remote.getAllAppointments(case)
    }
    suspend fun refreshDataBaseFromRemote(case:List<Appointment>){
        recordsDao.saveRecords(appointmentsToRecords(case))
    }
    suspend fun getPatientRecords(appointments:MutableLiveData<List<Appointment>>,id:String){
        val getAppointment = mutableListOf<Appointment>()
            val appointment = recordsDao.getRecordsByPatientId(id)
            if (appointment != null){
                for(app in appointment){
                    getAppointment.add(Appointment(app.date!!,app.dId!!,app.dName!!,app.id,app.pId!!,app.pName!!,app.title!!))
                }
            }
            Log.i(" testing", appointment.toString())
            appointments.value = getAppointment

    }
    suspend fun getDoctorRecords(appointments:MutableLiveData<List<Appointment>>,id:String){
        val getAppointment = mutableListOf<Appointment>()
        val appointment = recordsDao.getRecordsByDoctorId(id)
        if (appointment != null){
            for(app in appointment){
                getAppointment.add(Appointment(app.date!!,app.dId!!,app.dName!!,app.id,app.pId!!,app.pName!!,app.title!!))
            }
        }
        Log.i(" testing", appointment.toString())
        appointments.value = getAppointment

    }
    }

