package com.example.android.clinicapp

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android.clinicapp.data.dto.DaysDTO
import com.example.android.clinicapp.data.local.*
import com.example.android.clinicapp.data.local.DaysDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DaysDaoTest {
    //dAo
    private lateinit var daysDao: DaysDao
    //databases
    private lateinit var daysDb: DaysDb

    //Days prepared Data
    private val example = listOf<String>("1","1","1")
    private val exampleDayDto = DaysDTO("1",example,example,example,example,example,example,example)


    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        daysDb = Room.inMemoryDatabaseBuilder(context,DaysDb::class.java).build()
        //initialize DAOs
        daysDao = daysDb.DaysDao()

    }

    @After
    fun closeDb() {
        daysDb.close()
    }
    @Test
    fun testing() = runBlocking{
        Assert.assertEquals(1,1)
    }
    //first we test the days DataBase
    @Test
    fun daysInsertionTest() = runBlocking {
        //inserting
        daysDao.saveDays(exampleDayDto)
        //listing
        val list = daysDao.getDays()
        //it should have 3 elements in each column
        Assert.assertEquals(3, list[0].friday.size)
    }
    @Test
    fun daysClearTest() = runBlocking {
        //inserting
        daysDao.saveDays(exampleDayDto)
        //when clearing
        daysDao.clear()
        val list = daysDao.getDays()
        //it should have 3 elements in each column
        Assert.assertEquals(0, list.size)
    }

}