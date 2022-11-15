package com.example.pocketclinic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketclinic.ui.recolectionData
import com.example.pocketclinic.R
import com.google.firebase.database.*

class CalendarActivity : AppCompatActivity(){
    private  lateinit  var dbref : DatabaseReference
    private lateinit  var userRecyclerview : RecyclerView
    private lateinit  var userArrayList:  ArrayList <recolectionData>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        userRecyclerview = findViewById(R.id.pacientList)
        userRecyclerview.layoutManager = LinearLayoutManager(this)
        userRecyclerview.setHasFixedSize(true)


        /* userArrayList = arrayListOf<recolectionData>()*/
        userArrayList = arrayListOf<recolectionData>()
        getUserData()

    }

    private fun getUserData() {

        dbref = FirebaseDatabase.getInstance().getReference("Citas")

            .child("name")

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (nameSnapshot in snapshot.children){


                        val user = nameSnapshot.getValue(recolectionData::class.java)
                        userArrayList.add(user!!)


                    }

                    userRecyclerview.adapter = MyAdapter(userArrayList)


                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }
}