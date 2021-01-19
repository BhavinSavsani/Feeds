package com.bhavinpracticalinfosys.di.module

import com.bhavinpracticalinfosys.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * All your Activities participating in DI would be listed here.
 */
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

}
