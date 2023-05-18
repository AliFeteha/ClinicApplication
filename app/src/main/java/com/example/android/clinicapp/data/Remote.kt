package com.example.android.clinicapp.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.android.clinicapp.data.consts.*
import com.example.android.clinicapp.utils.TypeConverter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.math.log


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
        Log.i(" testing any thing", id)
        Log.i(" testing LiveData ", doctor.value.toString())
        remoteDataBase.child(Doctors).child(id).get().addOnSuccessListener {
                Log.i(Tag, "get i nto snapshot")
                for (childSnapshot in it.children) {
                    Log.i(" from the inside of the remote call ", childSnapshot.value.toString())
                    val value = childSnapshot.value
                    value?.let { list.add(value.toString()) }
                }
            if (list.size == 3)
                doctor.value = typeConverter.fromStringToMiniDoctor(list)
            if (list.size>0)
                doctor.value = typeConverter.fromStringToDoctor(list[0])

            else
                doctor.value = Doctor()
            Log.i(Tag, list.toString())
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

    fun overRide(doctor: Doctor,patient: Patient,oldEmail: String, flag: MutableLiveData<Boolean>){
        var firebaseControl: FirebaseControl
        val list: MutableList<String> = mutableListOf()
        remoteDataBase.child(Authentication).child(oldEmail).get().addOnSuccessListener {
            for (childSnapshot in it.children) {
                val value = childSnapshot.value
                value?.let { list.add(value.toString()) }
            }
            try {
                firebaseControl= FirebaseControl(list[0],list[1],list[2])
                remoteDataBase.child(Authentication).child(oldEmail).removeValue().addOnSuccessListener {
                    remoteDataBase.child(Authentication).child(firebaseControl.email!!).setValue(firebaseControl).addOnSuccessListener {
                        if (patient.id == null)
                            overRideDoctorCall(doctor,flag)
                        else{
                            overRidePatientCall(patient,flag)
                        }
                    }
                }
            }catch (_:Exception){}
        }
    }
    private fun overRideDoctorCall(doctor: Doctor, flag: MutableLiveData<Boolean>) {
        remoteDataBase.child(Doctors).child(doctor.id!!).setValue(doctor).addOnSuccessListener {
            flag.value = true
        }
    }
    private fun overRidePatientCall(patient: Patient, flag: MutableLiveData<Boolean>) {
        remoteDataBase.child(Patients).child(patient.id!!).setValue(patient).addOnSuccessListener {
            flag.value = true
        }
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
            if (list.size>0)
                firebaseControl.value = FirebaseControl(list[0],list[1],list[2])
            else
                firebaseControl.value = FirebaseControl(password = null)
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