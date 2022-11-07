package com.example.pocketclinic.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pocketclinic.adapters.PatAdapter
import com.example.pocketclinic.PatientDetailsActivity
import com.example.pocketclinic.models.PatientModel
import com.example.pocketclinic.R
import com.google.firebase.database.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


//Fetching Activity
class FetchingPatients : AppCompatActivity() {
    private lateinit var empRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var patList: ArrayList<PatientModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.buscar_expedientes)

        empRecyclerView = findViewById(R.id.rvEmp)
        empRecyclerView.layoutManager = LinearLayoutManager(this)
        empRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        patList = arrayListOf<PatientModel>()

        getEmployeesData()

    }

    private fun getEmployeesData() {

        empRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Patients")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                patList.clear()
                if (snapshot.exists()) {
                    for (patSnap in snapshot.children) {
                        val empData = patSnap.getValue(PatientModel::class.java)
                        patList.add(empData!!)
                    }
                    val mAdapter = PatAdapter(patList)
                    empRecyclerView.adapter = mAdapter


                    mAdapter.setOnItemClickListener(object : PatAdapter.onItemClickListener {
                        override fun onItemClick(position: Int) {
                        val intent = Intent(this@FetchingPatients, PatientDetailsActivity::class.java)


                            //put extras
                            intent.putExtra("patId", patList[position].patId)
                            intent.putExtra("patNombre", patList[position].patNombre)
                            intent.putExtra("patEgresoFecha", patList[position].patEgresoFecha)
                            intent.putExtra("patAlergy", patList[position].patAlergy)
                            intent.putExtra("patEgreso", patList[position].patEgreso)
                            intent.putExtra("patIngreso", patList[position].patIngreso)
                            intent.putExtra("patInstancia", patList[position].patInstancia)
                            intent.putExtra("patIngresoFecha", patList[position].patIngresoFecha)
                            intent.putExtra("patNacimiento", patList[position].patNacimiento)
                            intent.putExtra("patEstado", patList[position].patEstado)
                            intent.putExtra("patDireccion", patList[position].patDireccion)
                            intent.putExtra("patMunicipio", patList[position].patMunicipio)
                            intent.putExtra("patTelefono", patList[position].patTelefono)
                            intent.putExtra("patCorreo", patList[position].patCorreo)
                            startActivity(intent)
                        }

                    })

                    empRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}