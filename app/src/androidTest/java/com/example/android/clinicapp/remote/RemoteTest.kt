package com.example.android.clinicapp.remote
import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.clinicapp.data.Remote
import com.example.android.clinicapp.data.consts.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Before
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class RemoteTest {

    //Prepared data
    val something = "something"

    val sampleDoctor = Doctor(something,something,something,something,something,something,something,something,
        listOf(Days.Thursday, Days.Sunday))

    val medicalInsurance = MedicalInsurance("Lol Life Insurance","11124551645")
    val emergencyContact = EmergencyContact("Lol Life Insurance","11124551645")
    val medicalIssues = listOf("nothing")
    val samplePatient = Patient(something,something,something,something,something,emergencyContact,something,
        something,something,medicalInsurance,medicalIssues,"011221","Mr. LOL")

    lateinit var remote :Remote

    lateinit var email :String
    lateinit var password :String

    @Before
    fun setup(){

        Log.i("Remote Test","check In")
        remote = Remote()
        email = UUID.randomUUID().toString()
        password = UUID.randomUUID().toString()
    }

    @Test
    fun useAppContext() = runBlocking{
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.example.android.clinicapp", appContext.packageName)

    }
    @Test
    fun authenticationAndRegistrationDoctorTest() = runBlocking{
        //Given a valid email
        Log.i("Remote Test",email)
        Log.i("Remote Test",password)
        sampleDoctor.email = email

        //when signing up the doctor
        remote.signUpDoctor(sampleDoctor,password)
        val incomingAuth = remote.fireBaseAuthentication(email)
        Assert.assertEquals(incomingAuth.email, email)
        Assert.assertEquals(incomingAuth.password, password)

        Log.i("Remote Test",incomingAuth.email.toString())
        Log.i("Remote Test",incomingAuth.password.toString())
        val id = incomingAuth.id
        val incomingProfile  = remote.getDoctorProfile(id!!)

        Log.i("Remote Test",incomingAuth.id.toString())
        Assert.assertEquals(incomingProfile.email, email)
    }

    @Test
    fun authenticationAndRegistrationPatientTest() = runBlocking{
        //Given a valid email
        Log.i("Remote Test",email)
        Log.i("Remote Test",password)
        samplePatient.email = email

        //when signing up the doctor
        remote.signUpPatient(samplePatient,password)
        val incomingAuth = remote.fireBaseAuthentication(email)
        Assert.assertEquals(incomingAuth.email, email)
        Assert.assertEquals(incomingAuth.password, password)

        Log.i("Remote Test",incomingAuth.email.toString())
        Log.i("Remote Test",incomingAuth.password.toString())
        val id = incomingAuth.id
        val incomingProfile  = remote.getPatientProfile(id!!)

        Log.i("Remote Test",incomingAuth.id.toString())
        Assert.assertEquals(incomingProfile.email, email)
    }
    @Test
    fun getAllDoctorsTest() = runBlocking {
        val incoming = remote.getAllDoctors()
        //more than one profile has been registered
        Assert.assertNotEquals(0,incoming.size)
    }
}