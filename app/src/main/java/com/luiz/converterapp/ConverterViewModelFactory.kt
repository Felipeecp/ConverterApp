package com.luiz.converterapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.luiz.converterapp.data.ConverterRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class ConverterViewModelFactory @Inject constructor(private val repository: ConverterRepository):ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T = ConverterViewModel(repository) as T

}