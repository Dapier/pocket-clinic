package com.example.pocketclinic

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*

class CitationRepository {

    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("Citas")

    @Volatile private var INSTANCE : CitationRepository?= null

    fun getInstance() : CitationRepository?{
        return INSTANCE ?: synchronized(this){

            val instance = CitationRepository()
            INSTANCE = instance
            instance
        }


    }

    fun loadUsers(userList : MutableLiveData<List<citationData>>){

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                try {

                    val _pacientList : List<citationData> = snapshot.children.map { dataSnapshot ->

                        dataSnapshot.getValue(citationData::class.java)!!

                    }

                    userList.postValue(_pacientList)

                }catch (e : Exception){


                }


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })


    }







}