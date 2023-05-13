package com.example.android.clinicapp.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.android.clinicapp.App
import com.example.android.clinicapp.data.consts.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay

class remote {
    private fun stringToDaysList( string:String):List<Days>{
        val inputString = string
        val trimmedString = inputString.trim('[', ']')
        val elements = trimmedString.split(',')
        val list: List<String> = elements.map { it.trim() }
        val listDays : MutableList<Days> = mutableListOf()
        for(element in list ){
            if(element == "Saturday"){
                listDays.add(Days.Saturday)
            }else if(element == "Sunday") {
                listDays.add(Days.Sunday)
            }else if(element == "Monday") {
                listDays.add(Days.Monday)
            }else if(element == "Tuesday") {
                listDays.add(Days.Tuesday)
            }else if(element == "Wednesday") {
                listDays.add(Days.Wednesday)
            }else if(element == "Thursday") {
                listDays.add(Days.Thursday)
            }else{
                listDays.add(Days.Friday)
            }
        }
        return listDays
    }
    private fun stringToList( string:String):List<String>{
        val inputString = string
        val trimmedString = inputString.trim('[', ']')
        val elements = trimmedString.split(',')
        val list: List<String> = elements.map { it.trim() }
        return list
    }
    private fun stringToEmergencyContact(string: String): EmergencyContact{
        val trimmedString = string.trim('{', '}')
        val keyValuePairs = trimmedString.split(',')
        val values = keyValuePairs.map { it.split('=')[1].trim() }
        val emergencyContact : EmergencyContact = EmergencyContact(values[0],values[1]);
        return emergencyContact
    };
    private fun stringToMedicalInsurance(string: String): MedicalInsurance{
        val trimmedString = string.trim('{', '}')
        val keyValuePairs = trimmedString.split(',')
        val values = keyValuePairs.map { it.split('=')[1].trim() }
        val medicalInsurance : MedicalInsurance = MedicalInsurance(values[0],values[1]);
        return medicalInsurance
    };
    private fun listStringsToListAppointments(string: List<String>):MutableList<Appointment>{
        var appointments : MutableList<Appointment> = mutableListOf()
        for(str in string){
            val trimmedString = str.trim('{', '}')
            val keyValuePairs = trimmedString.split(',')
            val values = keyValuePairs.map { it.split('=')[1].trim() }
            val appointment : Appointment = Appointment(values[0],values[1],values[2],values[3],values[4],values[5],values[6])
            appointments.add(appointment)
        }
        return appointments
    }
    private fun listStringsToListDoctors(string: List<String>):MutableList<Doctor>{
        var doctors : MutableList<Doctor> = mutableListOf()
        for(str in string){
            val trimmedString = str.trim('[', ']')
            val keyValuePairs = trimmedString.split(',')
            val values = keyValuePairs.map { it.split('=')[1].trim() }
            val doctor:Doctor = Doctor(values[0],values[1],values[2],values[3],values[4],values[5],values[6],values[7],stringToDaysList(values[8]))
            doctors.add(doctor)
        }
        return doctors
    }
    private var remoteDataBase: DatabaseReference = Firebase.database.reference;
    fun signUpDoctor(doctor: Doctor,password:String){
        remoteDataBase.child("Doctors").child(doctor.id!!).setValue(doctor)
        val control:firebaseControl = firebaseControl(doctor.email,doctor.id,password)
        remoteDataBase.child("Authentication").child(doctor.email!!).setValue(control)
    }
    fun signUpPatient(patient: Patient,password:String){
        remoteDataBase.child("Patients").child(patient.id!!).setValue(patient)
        val control:firebaseControl = firebaseControl(patient.email,patient.id,password)
        remoteDataBase.child("Authentication").child(patient.email!!).setValue(control)
    }
    suspend fun getDoctorProfile(id:String):Doctor{
        Log.i("aaaaa","get i nto function")
        val list: MutableList<String> = mutableListOf()
        var doctor : Doctor = Doctor("","","","","","","", "",listOf());
        remoteDataBase.child("Doctors").child(id).get().addOnSuccessListener {
            Log.i("aaaaa","get i nto snapshot")
            for (childSnapshot in it.children) {
                val value = childSnapshot.value
                value?.let { list.add(value.toString()) }
            }
            Log.i("aaaaa",list.toString())
            doctor = Doctor(list[0],list[1],list[2],list[3],list[4],list[5],list[6],list[7],stringToDaysList(list[8]))
        }.addOnFailureListener {
            Log.i("aaaaaaaa", "Error getting data", it)
        }
        delay(2000)
        return doctor
    }
    suspend fun getPatientProfile(id:String):Patient{
        Log.i("aaaaa","get i nto function")
        val list: MutableList<String> = mutableListOf()
        val nsurance : MedicalInsurance = MedicalInsurance("","")
        val em : EmergencyContact = EmergencyContact("","")
        var patient : Patient = Patient("","","","","",em,"","","",nsurance, mutableListOf(),"","");
        remoteDataBase.child("Patients").child(id).get().addOnSuccessListener {
            Log.i("aaaaa","get i nto snapshot")
            for (childSnapshot in it.children) {
                val value = childSnapshot.value
                value?.let { list.add(value.toString()) }
            }
            Log.i("aaaaa",list.toString())
            patient = Patient(list[0],list[1],list[2],list[3],list[4],stringToEmergencyContact(list[5]),list[6],list[7],list[8],stringToMedicalInsurance(list[9]),stringToList(list[10]),list[11],list[12])
        }.addOnFailureListener {
            Log.i("aaaaaaaa", "Error getting data", it)
        }
        delay(2000)
        return patient
    }
    fun addAppointment(appointment: Appointment){
        remoteDataBase.child("Appointments").child(appointment.id).setValue(appointment)
    }
    suspend fun getAllAppointments():MutableList<Appointment>{
        val list: MutableList<String> = mutableListOf()
        var appointments : MutableList<Appointment> = mutableListOf();
        remoteDataBase.child("Appointments").get().addOnSuccessListener {
            Log.i("aaaaa","get i nto snapshot")
            for (childSnapshot in it.children) {
                val value = childSnapshot.value
                value?.let { list.add(value.toString()) }
            }
            Log.i("aaa",list.toString())
            appointments = listStringsToListAppointments(list)
        }.addOnFailureListener {
            Log.i("aaaaaaaa", "Error getting data", it)
        }
        delay(2000)
        return appointments
    }
    suspend fun getAllDoctors():MutableList<Doctor>{
        val list: MutableList<String> = mutableListOf()
        var doctors : MutableList<Doctor> = mutableListOf();
        remoteDataBase.child("Doctors").get().addOnSuccessListener {
            Log.i("aaaaa","get i nto snapshot")
            for (childSnapshot in it.children) {
                val value = childSnapshot.value
                value?.let { list.add(value.toString()) }
            }
            Log.i("aaa",list.toString())
            doctors = listStringsToListDoctors(list)
        }.addOnFailureListener {
            Log.i("aaaaaaaa", "Error getting data", it)
        }
        delay(2000)
        return doctors
    }
}