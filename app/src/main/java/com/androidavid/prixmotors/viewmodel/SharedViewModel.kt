package com.androidavid.prixmotors.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val clickCounterDis = MutableLiveData<Int>().apply { value = 0 }
}