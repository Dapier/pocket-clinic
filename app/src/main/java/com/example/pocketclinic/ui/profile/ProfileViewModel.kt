package com.example.pocketclinic.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class ProfileViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = FirebaseAuth.getInstance().currentUser?.displayName
    }
    val text: LiveData<String> = _text
}