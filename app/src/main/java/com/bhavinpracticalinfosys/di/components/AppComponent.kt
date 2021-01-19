package com.bhavinpracticalinfosys.di.components

import com.bhavinpracticalinfosys.InfosysApp
import com.bhavinpracticalinfosys.di.module.ActivityModule
import com.bhavinpracticalinfosys.di.module.AppModule
import com.bhavinpracticalinfosys.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityModule::class, ViewModelModule::class])
interface AppComponent : AndroidInjector<InfosysApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: InfosysApp): Builder

        fun build(): AppComponent
    }

    override fun inject(instance: InfosysApp)

}