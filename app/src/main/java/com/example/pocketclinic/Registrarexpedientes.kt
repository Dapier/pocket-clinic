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
    private lateinit var empList: ArrayList<PatientModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_expedientes)

        empRecyclerView = findViewById(R.id.rvEmp)
        empRecyclerView.layoutManager = LinearLayoutManager(this)
        empRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        empList = arrayListOf<PatientModel>()

        getEmployeesData()

    }

    private fun getEmployeesData() {

        empRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Patients")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                empList.clear()
                if (snapshot.exists()) {
                    for (empSnap in snapshot.children) {
                        val empData = empSnap.getValue(PatientModel::class.java)
                        empList.add(empData!!)
                    }
                    val mAdapter = PatAdapter(empList)
                    empRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : PatAdapter.onItemClickListener {
                        override fun onItemClick(position: Int) {

                            val intent = Intent(
                                this@Registrarexpedientes,
                                PatientDetailsActivity::class.java
                            )

                            //put extras
                            intent.putExtra("Nombre", empList[position].form_name)
                            intent.putExtra("Fecha Ingreso", empList[position].form_ingresofecha)
                            intent.putExtra("Fecha egreso", empList[position].form_egresofecha)
                            intent.putExtra("Diagnostico Ingreso", empList[position].form_ingreso)
                            intent.putExtra("Diagnostico Egreso", empList[position].form_egreso)
                            intent.putExtra("Alergias", empList[position].form_alergy)
                            intent.putExtra("Dias de instancia", empList[position].form_instancia)
                            intent.putExtra("Fecha Nacimiento", empList[position].form_nacimiento)
                            intent.putExtra("Correo", empList[position].form_correo)
                            intent.putExtra("Estado", empList[position].form_estado)
                            intent.putExtra("Municipio", empList[position].form_municipio)
                            intent.putExtra("Direccion", empList[position].form_direccion)
                            intent.putExtra("telefono", empList[position].form_telefono)
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