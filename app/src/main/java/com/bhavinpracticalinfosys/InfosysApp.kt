package com.bhavinpracticalinfosys

import com.bhavinpracticalinfosys.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class InfosysApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

}