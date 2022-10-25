package com.example.pocketclinic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CitationViewModel: ViewModel() {

    private val repository : CitationRepository
    private val _allUsers = MutableLiveData<List<citationData>>()
    val allUsers : LiveData<List<citationData>> = _allUsers


    init {

        repository = CitationRepository().getInstance()!!
        repository.loadUsers(_allUsers)

    }
}