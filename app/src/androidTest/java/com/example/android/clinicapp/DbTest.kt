package com.example.android.clinicapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.clinicapp.data.dto.DaysDTO
import com.example.android.clinicapp.data.local.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    //5 dataBases to be tested
    //dAo
    private lateinit var daysDao: DaysDao
    private lateinit var formDao: FormDao
    private lateinit var recordsDao: RecordsDao
    private lateinit var patientsDao: PatientsDao
    private lateinit var doctorsDao: DoctorsDao
    //databases
    private lateinit var daysDb: DaysDb
    private lateinit var formDatabase: FormDatabase
    private lateinit var recordsDatabase: RecordsDatabase
    private lateinit var patientsDatabase: PatientsDatabase
    private lateinit var doctorsDatabase: DoctorsDatabase

    private val example = listOf<String>("sgldm","ksdflk","sdnfsn")

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        recordsDatabase = Room.inMemoryDatabaseBuilder(context,RecordsDatabase::class.java).build()
        daysDb = Room.inMemoryDatabaseBuilder(context,DaysDb::class.java).build()
        formDatabase = Room.inMemoryDatabaseBuilder(context,FormDatabase::class.java).build()
        recordsDatabase = Room.inMemoryDatabaseBuilder(context,RecordsDatabase::class.java).build()
        patientsDatabase = Room.inMemoryDatabaseBuilder(context,PatientsDatabase::class.java).build()
        doctorsDatabase = Room.inMemoryDatabaseBuilder(context,DoctorsDatabase::class.java).build()
        //initialize DAOs
        daysDao = daysDb.DaysDao()
        formDao = formDatabase.formDao()
        recordsDao = recordsDatabase.recordsDao()
        patientsDao = patientsDatabase.userPDao()
        doctorsDao =  doctorsDatabase.userDDao()

    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        daysDb.close()
        formDatabase.close()
        recordsDatabase.close()
        patientsDatabase.close()
        doctorsDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    suspend fun days() {
        //inserting
        daysDao.saveDays(DaysDTO("1",example,example,example,example,example,example,example))
        //listing
        val list = daysDao.getDays()
        //it should have 3 elements in each column
        Assert.assertEquals(3, list[0].friday.size)

    }
}