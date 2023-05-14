package com.example.android.clinicapp

import android.app.Application
import android.text.format.DateFormat
import com.example.android.clinicapp.auth.LoginViewModel
import com.example.android.clinicapp.dashboard.DashboardViewModel
import com.example.android.clinicapp.data.local.LocalDB
import com.example.android.clinicapp.patiant.appointments.AppontmentsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App:Application() {
    override fun onCreate() {
        super.onCreate()

        val myModule = module {
            //Declare a ViewModel - be later inject into Fragment with dedicated injector using by viewModel()
            single {LoginViewModel(get())}
            single {DashboardViewModel(get())}
            single { AppontmentsViewModel(get()) }
            single{LocalDB.createDaysDao(this@App)}
            single{LocalDB.createFormDao(this@App)}
            single{LocalDB.createDoctorDao(this@App)}
            single{LocalDB.createPatientDao(this@App)}
            single{LocalDB.createRecordsDao(this@App)}
        }

        startKoin {
            androidContext(this@App)
            modules(listOf(myModule))
        }
    }
}