package com.example.android.clinicapp.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android.clinicapp.data.consts.Days
import com.example.android.clinicapp.data.dto.DoctorsDTO
import com.example.android.clinicapp.data.local.DoctorsDao
import com.example.android.clinicapp.data.local.DoctorsDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DoctorsDaoTest {

    private lateinit var database: DoctorsDatabase
    private lateinit var dao: DoctorsDao

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context,DoctorsDatabase::class.java).build()
        dao = database.userDDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testInsertAndGetProfileById () = runBlocking{
        val doctor = DoctorsDTO(
            id = "1",
            name = "John Doe",
            gender = "Male",
            workDays = listOf(Days.Monday, Days.Wednesday, Days.Friday),
            email = "johndoe@example.com",
            img_url = "https://example.com/johndoe.jpg",
            city = "New York",
            telephone = "1234567890",
            address = "123 Main St"
        )

        dao.saveRecord(doctor)

        val result = dao.getProfileById(doctor.id)

        assertEquals(doctor.name, result.name)
        assertEquals(doctor.gender, result.gender)
        assertEquals(doctor.workDays, result.workDays)
        assertEquals(doctor.email, result.email)
        assertEquals(doctor.img_url, result.img_url)
        assertEquals(doctor.city, result.city)
        assertEquals(doctor.telephone, result.telephone)
        assertEquals(doctor.address, result.address)
    }

    @Test
    fun testClear() = runBlocking {
        val doctor1 = DoctorsDTO(
            id = "1",
            name = "John Doe",
            gender = "Male",
            workDays = listOf(Days.Monday, Days.Wednesday, Days.Friday),
            email = "johndoe@example.com",
            img_url = "https://example.com/johndoe.jpg",
            city = "New York",
            telephone = "1234567890",
            address = "123 Main St"
        )

        val doctor2 = DoctorsDTO(
            id = "2",
            name = "Jane Smith",
            gender = "Female",
            workDays = listOf(Days.Tuesday, Days.Thursday),
            email = "janesmith@example.com",
            img_url = "https://example.com/janesmith.jpg",
            city = "Los Angeles",
            telephone = "0987654321",
            address = "456 Elm St"
        )

        dao.saveRecord(doctor1)
        dao.saveRecord(doctor2)

        dao.clear()

        val result = dao.getProfileById(doctor1.id)

        assertNull(result)
    }

    @Test
    fun getDoctorByDaysWorks() = runBlocking {
        val doctor1 = DoctorsDTO(
            id = "1",
            name = "John Doe",
            gender = "Male",
            workDays = listOf(Days.Saturday),
            email = "johndoe@example.com",
            img_url = "https://example.com/johndoe.jpg",
            city = "New York",
            telephone = "1234567890",
            address = "123 Main St"
        )

        dao.saveRecord(doctor1)
        val result = dao.getProfileByDays(listOf(Days.Saturday))
        assertEquals(result,doctor1)
    }
}
