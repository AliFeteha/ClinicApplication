package com.example.android.clinicapp.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.android.clinicapp.data.consts.*
import com.example.android.clinicapp.utils.TypeConverter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import java.util.*


class Remote {
    val typeConverter = TypeConverter()
    private var remoteDataBase: DatabaseReference = Firebase.database.reference
    val doctors :MutableLiveData<List<Doctor>> = MutableLiveData()
    val patients :MutableLiveData<List<Patient>> = MutableLiveData()
    val doctor :MutableLiveData<Doctor> = MutableLiveData()
    val patient :MutableLiveData<Patient> = MutableLiveData()
    val appointments: MutableLiveData<List<Appointment>> = MutableLiveData()
    private fun generateId():String = UUID.randomUUID().toString()

    fun signUpDoctor(doctor: Doctor,password:String){
        doctor.id = generateId()
        remoteDataBase.child(Doctors).child(doctor.id!!).setValue(doctor)
        val control = FirebaseControl(doctor.email,doctor.id,password)
        remoteDataBase.child("Authentication").child(doctor.email!!).setValue(control)
    }

    fun signUpPatient(patient: Patient,password:String){
        patient.id = generateId()
        remoteDataBase.child(Patients).child(patient.id!!).setValue(patient)
        val control = FirebaseControl(email = patient.email,patient.id,password)
        remoteDataBase.child("Authentication").child(patient.email!!).setValue(control)
    }

    fun getDoctorProfile(doctor: MutableLiveData<Doctor>,id:String)
    {
        Log.i(Tag,"get i nto function")
        val list: MutableList<String> = mutableListOf()
        remoteDataBase.child(Doctors).child(id).get().addOnSuccessListener {
                Log.i(Tag, "get i nto snapshot")
                for (childSnapshot in it.children) {
                    val value = childSnapshot.value
                    value?.let { list.add(value.toString()) }
                }
                Log.i(Tag, list.toString())

            if (list.size == 3)
                doctor.value = typeConverter.fromStringToMiniDoctor(list)
            if (list.size>0)
                doctor.value = Doctor(
                    list[0], list[1], list[2], list[3], list[4],
                    list[5], list[6], list[7], typeConverter.stringToDaysList(list[8])
                )
            else
                doctor.value = Doctor()
            }.addOnFailureListener {
                Log.i(Tag, "Error getting data", it)
            }
    }

    fun getPatientProfile(patient: MutableLiveData<Patient>,id:String){
        Log.i(Tag,"get i nto function")
        val list: MutableList<String> = mutableListOf()
        remoteDataBase.child(Patients).child(id).get().addOnSuccessListener {
            Log.i(Tag,"get i nto snapshot")
            for (childSnapshot in it.children) {
                val value = childSnapshot.value
                value?.let { list.add(value.toString()) }
            }
            Log.i(Tag,list.toString())
            if (list.size == 3)
                patient.value = typeConverter.fromStringToMiniPatient(list)
            else if (list.size>0)
                patient.value = typeConverter.fromStringToPatient(list)
            else
                patient.value = Patient()
        }.addOnFailureListener {
            Log.i(Tag, "Error getting data", it)
        }
    }

    fun overRideDoctor(doctor: Doctor, id: String?){
        remoteDataBase.child(Doctors).child(id!!).setValue(doctor)
    }

    fun overRidePatient(patient: Patient, id: String?){
        remoteDataBase.child(Patients).child(id!!).setValue(patient)
    }

    fun addAppointment(appointment: Appointment){
        remoteDataBase.child("Appointments").child(appointment.id).setValue(appointment)
    }

    fun getAllAppointments(case:MutableLiveData<List<Appointment>>){
        Log.i(" gettt","git from firebase")
        val list: MutableList<String> = mutableListOf()
        remoteDataBase.child("Appointments").get().addOnSuccessListener {
            Log.i(" getttttttttt","get i nto snapshot")
            for (childSnapshot in it.children) {
                val value = childSnapshot.value
                value?.let { list.add(value.toString()) }
            }
            Log.i(" remote",list.toString())
            case.value  = typeConverter.listStringsToListAppointments(list)
            Log.i(" remote",case.value.toString())

        }.addOnFailureListener {
            Log.i(" getttttttttt", "Error getting data", it)
        }
    }
    fun getAllDoctors(doctor:MutableLiveData<List<Doctor>>){
        val list: MutableList<String> = mutableListOf()
        remoteDataBase.child("Doctors").get().addOnSuccessListener {
            for (childSnapshot in it.children) {
                val value = childSnapshot.value
                value?.let { list.add(value.toString()) }
            }
            Log.i(" aloooo from remote",list.toString())
            Log.i(" aloooo from remote",typeConverter.listStringsToListDoctors(list).toString())
            doctor.value = typeConverter.listStringsToListDoctors(list)

        }.addOnFailureListener {
            Log.i(" getttttttttt", "Error getting data", it)
        }
    }

    fun fireBaseAuthentication(firebaseControl:MutableLiveData<FirebaseControl>,email : String){
        val list: MutableList<String> = mutableListOf()
        try {
        remoteDataBase.child("Authentication").child(email).get().addOnSuccessListener {
            Log.i(Tag,"get i nto snapshot")
            for (childSnapshot in it.children) {
                val value = childSnapshot.value
                value?.let { list.add(value.toString()) }
            }
            Log.i("aaa",list.toString())
            if (list.size>0)
                firebaseControl.value = FirebaseControl(list[0],list[1],list[2])
            else
                firebaseControl.value = FirebaseControl()
        }.addOnFailureListener {
            Log.i(Tag, "Error getting data", it)
        }
        }catch (ignored:Exception){}
    }

    companion object{
        const val Tag = "Remote Class"
        const val connectionFailed = "Connection Failed"
        const val Authentication = "Authentication"
        const val Appointments = "Appointments"
        const val Patients = "Patients"
        const val Doctors = "Doctors"
    }
}