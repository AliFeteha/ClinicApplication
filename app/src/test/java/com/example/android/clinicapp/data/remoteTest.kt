package com.example.android.clinicapp.data

import com.example.android.clinicapp.data.consts.Days
import com.example.android.clinicapp.data.consts.Doctor
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class remoteTest {

    @Test
    fun signUpDoctor() {

    }

    @Test
    fun signUpPatient() {
    }
    @Before
    fun setupFirebase() {
        var remoteDataBase: DatabaseReference = Firebase.database.reference;
    }
    @Test
    suspend fun getDoctorProfile() {
        var doc:Doctor = Doctor("ali","ali","ali","llll","ali","ali","ali", listOf(Days.Saturday,Days.Friday))
        val r = remote()
        assertEquals(doc, r.getDoctorProfile("llll"))
    }

    @Test
    fun getPatientProfile() {
    }

    @Test
    fun addAppointment() {
    }

    @Test
    fun getAllAppointments() {
    }

    @Test
    fun getAllDoctors() {
    }
}