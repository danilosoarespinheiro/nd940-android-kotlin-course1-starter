package com.udacity.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    private val _shoes = MutableLiveData<MutableList<Shoe>>()
    val shoes: LiveData<MutableList<Shoe>> get() = _shoes

    val fromShoeDetailToShoeList = MutableLiveData<Boolean>()

    val cancelFromShoeDetailToShoeList = MutableLiveData<Boolean>()

    init {
        _shoes.value = mutableListOf()
    }

    fun addNewShoe(shoe: Shoe) {
        _shoes.value?.add(shoe)
        fromShoeDetailToShoeList.value = true
    }

    fun onAddShoeComplete() {
        fromShoeDetailToShoeList.value = false
    }
    fun onCancel() {
        cancelFromShoeDetailToShoeList.value = true
    }
    fun onCancelComplete() {
        cancelFromShoeDetailToShoeList.value = false
    }
}