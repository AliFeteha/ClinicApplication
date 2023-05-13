package com.example.android.clinicapp.database

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android.clinicapp.data.dto.RecordsDTO
import com.example.android.clinicapp.data.local.RecordsDao
import com.example.android.clinicapp.data.local.RecordsDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class RecordsDaoTest {

    private lateinit var database: RecordsDatabase
    private lateinit var recordsDao: RecordsDao

    @Before
    fun createDb() {

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, RecordsDatabase::class.java).build()
        recordsDao = database.recordsDao()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testSaveAndGetRecords() = runBlocking {
        // Create a test record
        val record = RecordsDTO(
            title = true,
            pName = "John",
            pId = "1",
            dName = "Dr. Smith",
            date = "2022-05-13",
            dId = "2",
            id = "123"
        )

        // Save the record to the database
        recordsDao.saveRecord(record)

        // Get the list of records from the database
        val records = recordsDao.getRecords()

        // Verify that the list contains the saved record
        Assert.assertEquals(true, records.contains(record))
    }

    @Test
    fun testGetRecordsByPatientId() = runBlocking {
        // Create a test record
        val record = RecordsDTO(
            title = true,
            pName = "John",
            pId = "1",
            dName = "Dr. Smith",
            date = "2022-05-13",
            dId = "2",
            id = "123"
        )

        // Save the record to the database
        recordsDao.saveRecord(record)

        // Get the record by patient ID from the database
        val result = recordsDao.getRecordsByPatientId("1")

        // Verify that the result is the saved record
        Assert.assertEquals(result, record)
    }

    @Test
    fun testGetRecordsByDoctorId() = runBlocking {
        // Create a test record
        val record = RecordsDTO(
            title = true,
            pName = "John",
            pId = "1",
            dName = "Dr. Smith",
            date = "2022-05-13",
            dId = "2",
            id = "123"
        )

        // Save the record to the database
        recordsDao.saveRecord(record)

        // Get the record by doctor ID from the database
        val result = recordsDao.getRecordsByDoctorId("2")

        // Verify that the result is the saved record
        Assert.assertEquals(result, record)
    }

    @Test
    fun testClear() = runBlocking {
        // Create some test records
        val record1 = RecordsDTO(
            title = true,
            pName = "John",
            pId = "1",
            dName = "Dr. Smith",
            date = "2022-05-13",
            dId = "2",
            id = "123"
        )
        val record2 = RecordsDTO(
            title = false,
            pName = "Jane",
            pId = "3",
            dName = "Dr. Lee",
            date = "2022-05-14",
            dId = "4",
            id = "456"
        )

        // Save the records to the database
        recordsDao.saveRecord(record1)
        recordsDao.saveRecord(record2)

        // Clear the database
        recordsDao.clear()

        // Get the list of records from the database
        val records = recordsDao.getRecords()

        // Verify that the list is empty
        Assert.assertEquals(0,records.size)

    }
}
