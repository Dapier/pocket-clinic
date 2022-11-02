package com.example.pocketclinic

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*


//Fetching Activity
class Registrarexpedientes : AppCompatActivity() {
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
                    for (empSnap in snapshot.children) {
                        val empData = empSnap.getValue(PatientModel::class.java)
                        patList.add(empData!!)
                    }
                    val mAdapter = PatAdapter(patList)
                    empRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : PatAdapter.onItemClickListener {
                        override fun onItemClick(position: Int) {



                            //put extras
                            intent.putExtra("patId", patList[position].patId)
                            intent.putExtra("Nombre", patList[position].patNombre)
                            intent.putExtra("Fecha Ingreso", patList[position].patEgresoFecha)
                            intent.putExtra("Fecha egreso", patList[position].patAlergy)
                            intent.putExtra("Diagnostico Ingreso", patList[position].patEgreso)
                            intent.putExtra("Diagnostico Egreso", patList[position].patIngreso)
                            intent.putExtra("Alergias", patList[position].patInstancia)
                            intent.putExtra("Dias de instancia", patList[position].patIngresoFecha)
                            intent.putExtra("Fecha Nacimiento", patList[position].patNacimiento)
                            intent.putExtra("Correo", patList[position].patEstado)
                            intent.putExtra("Estado", patList[position].patDireccion)
                            intent.putExtra("Municipio", patList[position].patMunicipio)
                            intent.putExtra("Direccion", patList[position].patTelefono)
                            intent.putExtra("telefono", patList[position].patCorreo)
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