package com.luiz.converterapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luiz.converterapp.data.Conversion
import com.luiz.converterapp.data.ConversionResult
import com.luiz.converterapp.data.ConverterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConverterViewModel(private val repository: ConverterRepository):ViewModel() {

    val selectedConversion: MutableState<Conversion?> = mutableStateOf(null)
    val inputText: MutableState<String> = mutableStateOf("")

    val typedValue = mutableStateOf("0.0")


    fun getConversions() = listOf(
        Conversion(1,"Libras para Quilogramas","lbs","kg",0.453592),
        Conversion(2,"Quilogramas para Libras","kg","lbs",2.20462),
        Conversion(3,"Jardas para Metros","yd","m",0.9144),
        Conversion(4,"Metros para Jardas","m","yd",1.09361),
        Conversion(5,"Milhas para Quilometros","mi","km",1.60934),
        Conversion(6,"Qilometros para Milhas","km","mi",0.621371)
    )

    val resultList = repository.getSavedResults()

    fun addResult(message1:String,message2:String){
       viewModelScope.launch(Dispatchers.IO) {
           repository.insertResult(ConversionResult(0,message1,message2))
       }
    }

    fun removeResult(item: ConversionResult){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteResult(item)
        }
    }

    fun clearAll(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllResults()
        }
    }

}