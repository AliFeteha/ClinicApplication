package com.example.android.clinicapp

import android.app.Application
import com.example.android.clinicapp.auth.LoginViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App:Application() {
    override fun onCreate() {
        super.onCreate()

        /**
         * use Koin Library as a service locator
         */
        val myModule = module {
            //Declare a ViewModel - be later inject into Fragment with dedicated injector using by viewModel()
            single {LoginViewModel(get())}
        }

        startKoin {
            androidContext(this@App)
            modules(listOf(myModule))
        }
    }
}