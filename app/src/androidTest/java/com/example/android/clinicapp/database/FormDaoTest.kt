package com.example.android.clinicapp.database

import android.util.Log
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android.clinicapp.data.consts.Comment
import com.example.android.clinicapp.data.dto.FormDTO
import com.example.android.clinicapp.data.local.FormDao
import com.example.android.clinicapp.data.local.FormDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class FormDaoTest {
    private lateinit var database:FormDatabase
    private lateinit var formDao:FormDao

    //Form prepared Data
    private val value = "example"
    private val comment = Comment("44",value,value,value)
    private val formDTO = FormDTO("1",value,value,value,value, listOf(comment,comment,comment))
    private val formDTO2 = FormDTO("2",value,value,value,value, listOf(comment,comment,comment))
    private val formDTO3 = FormDTO("3",value,value,value,value, listOf(comment,comment,comment))



    @Before
    fun setup(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, FormDatabase::class.java).build()
        formDao = database.formDao()

    }

    @After
    fun tearDown() {
        database.close()
    }


    //Form DataBase
    @Test
    fun formInsertionTest() = runBlocking {
        //inserting
        formDao.saveQuestion(formDTO)
        //listing
        val list = formDao.getForm()

        //it should have 3 elements in each column
        Assert.assertEquals(formDTO.id, list[0].id)
    }
    @Test
    fun formClearTest() = runBlocking {
        //inserting
        formDao.saveQuestion(formDTO)
        //when clearing
        formDao.clear()
        val list = formDao.getForm()
        //it should have 3 elements in each column
        Assert.assertEquals(0, list.size)
    }
    @Test
    fun formFetchTest() = runBlocking {
        //inserting
        formDao.saveQuestion(formDTO)
        formDao.saveQuestion(formDTO2)
        formDao.saveQuestion(formDTO3)
        //when extracting item id 2
        formDao.delete(formDTO.id)
        val list  = formDao.getForm()
        for (formD in list) {
            Assert.assertNotEquals(formDTO,formD)
        }
    }
    @Test
    fun formAppendCommentTest() = runBlocking {
        //inserting
        formDao.saveQuestion(formDTO)
        var list  = formDao.getForm()
        //getting id of the item
        val id = list[0].id
        val exComment = comment
        exComment.body = "unique"
        formDao.appendComment(id,exComment)
        //after modification
        list = formDao.getForm()
        //it should have the same body as the appended
        Assert.assertEquals("unique", list[0].comments?.get(3)?.body.toString())
    }

}