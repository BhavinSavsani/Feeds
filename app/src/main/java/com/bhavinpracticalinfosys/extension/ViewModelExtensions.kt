package com.bhavinpracticalinfosys.extension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bhavinpracticalinfosys.ui.base.BaseActivity

/**
 * Synthetic sugaring to get instance of [ViewModel] for [AppCompatActivity].
 */
inline fun <reified T : ViewModel> BaseActivity.getViewModel(): T {
    return ViewModelProvider(this, viewModelFactory).get(T::class.java)
}

