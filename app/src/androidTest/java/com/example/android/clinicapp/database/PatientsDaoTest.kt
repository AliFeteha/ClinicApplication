package com.example.android.clinicapp.database

import android.util.Log
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android.clinicapp.data.consts.EmergencyContact
import com.example.android.clinicapp.data.consts.MedicalInsurance
import com.example.android.clinicapp.data.dto.PatientsDTO
import com.example.android.clinicapp.data.local.PatientsDao
import com.example.android.clinicapp.data.local.PatientsDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PatientsDaoTest {
    private lateinit var database: PatientsDatabase
    private lateinit var patientsDao: PatientsDao

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, PatientsDatabase::class.java).build()
        patientsDao = database.userPDao()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testSaveRecordAndGetProfileById() = runBlocking {
        val patient = PatientsDTO(
            "1",
            "John Doe",
            "Male",
            "johndoe@example.com",
            "1990-01-01",
            "https://example.com/image.jpg",
            "123 Main St",
            "New York",
            "+1 (123) 456-7890",
            "O+",
            listOf("High blood pressure", "Diabetes"),
            EmergencyContact("Jane Doe", "+1 (123) 456-7890"),
            MedicalInsurance("Blue Cross", "12345")
        )
        patientsDao.saveRecord(patient)
        val loadedPatient = patientsDao.getProfileById("1")
        assertEquals(patient, loadedPatient)
    }

    @Test
    fun testClear() = runBlocking {
        patientsDao.saveRecord(PatientsDTO("1", "John Doe", "Male", null, null, null, null, null, null, null, emptyList(), null, null))
        patientsDao.saveRecord(PatientsDTO("2", "Jane Doe", "Female", null, null, null, null, null, null, null, emptyList(), null, null))
        patientsDao.clear()
        Log.i("test",patientsDao.getProfileById("1").toString())
        assertNull(patientsDao.getProfileById("1"))
        assertNull(patientsDao.getProfileById("2"))
    }
}
