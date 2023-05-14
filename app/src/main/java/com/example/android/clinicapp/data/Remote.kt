package com.example.android.clinicapp.data

import android.util.Log
import com.example.android.clinicapp.data.consts.*
import com.example.android.clinicapp.utils.TypeConverter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import java.util.UUID

class Remote {
    val typeConverter = TypeConverter()

    private var remoteDataBase: DatabaseReference = Firebase.database.reference;

    private fun generateId():String = UUID.randomUUID().toString()

    fun signUpDoctor(doctor: Doctor,password:String){
        doctor.id = generateId()
        remoteDataBase.child("Doctors").child(doctor.id!!).setValue(doctor)
        val control = FirebaseControl(doctor.email,doctor.id,password)
        remoteDataBase.child("Authentication").child(doctor.email!!).setValue(control)
    }

    fun signUpPatient(patient: Patient,password:String){
        patient.id = generateId()
        remoteDataBase.child("Patients").child(patient.id!!).setValue(patient)
        val control = FirebaseControl(patient.email,patient.id,password)
        remoteDataBase.child("Authentication").child(patient.email!!).setValue(control)
    }

    suspend fun getDoctorProfile(id:String) :Doctor
    {
        Log.i(Tag,"get i nto function")
        val list: MutableList<String> = mutableListOf()
        var doctor = Doctor()
        runBlocking {
            launch(Dispatchers.Unconfined) {
                remoteDataBase.child("Doctors").child(id).get().addOnSuccessListener {
                    Log.i(Tag, "get i nto snapshot")
                    for (childSnapshot in it.children) {
                        val value = childSnapshot.value
                        value?.let { list.add(value.toString()) }
                    }
                    Log.i(Tag, list.toString())
                    doctor = Doctor(
                        list[0], list[1], list[2], list[3], list[4],
                        list[5], list[6], list[7], typeConverter.stringToDaysList(list[8])
                    )

                }.addOnFailureListener {
                    Log.i(Tag, "Error getting data", it)
                }
            }
            delay(1500)
        }
        return doctor
    }

    suspend fun getPatientProfile(id:String):Patient{
        Log.i(Tag,"get i nto function")
        val list: MutableList<String> = mutableListOf()
        val medicalInsurance : MedicalInsurance = MedicalInsurance("","")
        val emergencyContact : EmergencyContact = EmergencyContact("","")
        var patient : Patient = Patient("","","","","",emergencyContact,"","","",medicalInsurance, mutableListOf(),"","");
        remoteDataBase.child("Patients").child(id).get().addOnSuccessListener {
            Log.i(Tag,"get i nto snapshot")
            for (childSnapshot in it.children) {
                val value = childSnapshot.value
                value?.let { list.add(value.toString()) }
            }
            Log.i(Tag,list.toString())
            patient = typeConverter.fromStringToPatient(list)

        }.addOnFailureListener {
            Log.i(Tag, "Error getting data", it)
        }
        delay(1500)
        return patient
    }

    fun addAppointment(appointment: Appointment){
        remoteDataBase.child("Appointments").child(appointment.id).setValue(appointment)
    }

    suspend fun getAllAppointments():List<Appointment>{
        val list: MutableList<String> = mutableListOf()
        var appointments : List<Appointment> = mutableListOf();
        remoteDataBase.child("Appointments").get().addOnSuccessListener {
            Log.i(Tag,"get i nto snapshot")
            for (childSnapshot in it.children) {
                val value = childSnapshot.value
                value?.let { list.add(value.toString()) }
            }
            Log.i(Tag,list.toString())
            appointments = typeConverter.listStringsToListAppointments(list)
        }.addOnFailureListener {
            Log.i(Tag, "Error getting data", it)
        }
        delay(1400)
        return appointments
    }

    suspend fun getAllDoctors():List<Doctor>{

        val list: MutableList<String> = mutableListOf()
        val dataSnapshot:MutableIterable<DataSnapshot>
        runBlocking {
            launch(Dispatchers.Unconfined) {

                    remoteDataBase.child("Doctors").get()
                        .addOnFailureListener { Log.i(Tag, connectionFailed) }
                        .addOnSuccessListener {
                            for (childSnapshot in it.children) {
                                val value = childSnapshot.value
                                value?.let { list.add(value.toString()) }
                            }
                        }

                delay(2000)
            }
            }


            return typeConverter.listStringsToListDoctors(list)
    }

    suspend fun fireBaseAuthentication(email : String):FirebaseControl {
        val list: MutableList<String> = mutableListOf()
        var firebaseControl = FirebaseControl("","","")
        remoteDataBase.child("Authentication").child(email).get().addOnSuccessListener {
            Log.i(Tag,"get i nto snapshot")
            for (childSnapshot in it.children) {
                val value = childSnapshot.value
                value?.let { list.add(value.toString()) }
            }
            Log.i("aaa",list.toString())
            firebaseControl = FirebaseControl(list[0],list[1],list[2])
        }.addOnFailureListener {
            Log.i(Tag, "Error getting data", it)
        }
        delay(1400)
        return firebaseControl
    }

    companion object{
        const val Tag = "Remote Class"
        const val connectionFailed = "Connection Failed"
    }
}