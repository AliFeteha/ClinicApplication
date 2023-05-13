package com.example.android.clinicapp

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.clinicapp.data.dto.RecordsDTO
import com.example.android.clinicapp.data.local.RecordsDatabase
import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() = runBlocking{
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val recordsDatabase = Room.inMemoryDatabaseBuilder(appContext, RecordsDatabase::class.java).build()
        val recordsDao = recordsDatabase.recordsDao()
        recordsDao.saveRecord(RecordsDTO(false,id= "lol", pName = "sdf", date = "s/df", dId = "dsfd", dName = "dfsf", pId = "dsfsdf"))
        assertEquals("com.example.android.clinicapp", appContext.packageName)

    }
}