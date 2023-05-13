package com.example.android.clinicapp.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.clinicapp.BuildConfig
import com.example.android.clinicapp.auth.LoginViewModel
import com.example.android.clinicapp.data.dto.DaysDTO
import com.example.android.clinicapp.data.local.*
import junit.framework.TestCase
import junitparams.Parameters
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [30])
class DatabaseTest: TestCase() {
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
        recordsDatabase = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),RecordsDatabase::class.java).build()
        daysDb = Room.inMemoryDatabaseBuilder(context,DaysDb::class.java).build()
        formDatabase = Room.inMemoryDatabaseBuilder(context,FormDatabase::class.java).build()
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
    fun closeDb() {
        stopKoin()
        daysDb.close()
        formDatabase.close()
        recordsDatabase.close()
        patientsDatabase.close()
        doctorsDatabase.close()
    }

    @Test
    fun days() = runBlocking {

        //inserting
        daysDao.saveDays(DaysDTO("1",example,example,example,example,example,example,example))
        //listing
        val list = daysDao.getDays()
        //it should have 3 elements in each column
        Assert.assertEquals(3, list[0].friday.size)

    }

    @Test
    fun daysInsertion() = runBlocking {

        //inserting
        daysDao.saveDays(DaysDTO("1",example,example,example,example,example,example,example))

    }
}