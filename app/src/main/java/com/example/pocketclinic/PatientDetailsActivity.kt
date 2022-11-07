package com.example.pocketclinic

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.pocketclinic.activities.FetchingPatients
import com.example.pocketclinic.models.PatientModel
import com.google.firebase.database.FirebaseDatabase

class PatientDetailsActivity : AppCompatActivity(){
    private lateinit var tvPatId: TextView
    private lateinit var tvPatNombre: TextView
    private lateinit var tvPatFechaEgreso: TextView
    private lateinit var tvPatFechaIngreso: TextView
    private lateinit var tvPatAlergias: TextView
    private lateinit var tvPatDInstancia: TextView
    private lateinit var tvPatDiagI: TextView
    private lateinit var tvPatDiagE: TextView
    private lateinit var tvPatEstado: TextView
    private lateinit var tvPatMunicipio: TextView
    private lateinit var tvPatNacimiento: TextView
    private lateinit var tvPatDireccion: TextView
    private lateinit var tvPatTelefono: TextView
    private lateinit var tvPatCorreo: TextView

    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.patientdetailsactivity)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("patId").toString(),
                intent.getStringExtra("patNombre").toString()
            )
        }
        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("patId").toString()
            )
        }
    }

    private fun openUpdateDialog(
        patId: String,
        patNombre: String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog, null)

        mDialog.setView(mDialogView)

        val etPatName = mDialogView.findViewById<EditText>(R.id.etPatName)
        val etPatFechaEgreso = mDialogView.findViewById<EditText>(R.id.etPatFechaEgreso)
        val etPatFechaIngreso = mDialogView.findViewById<EditText>(R.id.etPatFechaIngreso)
        val etDiagEgreso = mDialogView.findViewById<EditText>(R.id.etPatDiagE)
        val etDiagIngreso = mDialogView.findViewById<EditText>(R.id.etPatDiagI)
        val etDireccion = mDialogView.findViewById<EditText>(R.id.etPatDireccion)
        val etTelefono = mDialogView.findViewById<EditText>(R.id.etPatTelefono)
        val etCorreo = mDialogView.findViewById<EditText>(R.id.etPatCorreo)
        val etEstado = mDialogView.findViewById<EditText>(R.id.etPatEstado)
        val etMunicipio = mDialogView.findViewById<EditText>(R.id.etPatMunicipio)
        val etAlergias = mDialogView.findViewById<EditText>(R.id.etPatAlergias)
        val etInstancia = mDialogView.findViewById<EditText>(R.id.etPatInstancia)
        val etNacimiento = mDialogView.findViewById<EditText>(R.id.etPatNacimiento)


        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)


        etPatName.setText(intent.getStringExtra("patNombre").toString())
        etPatFechaEgreso.setText(intent.getStringExtra("patEgresoFecha").toString())
        etPatFechaIngreso.setText(intent.getStringExtra("patIngresoFecha").toString())
        etDiagEgreso.setText(intent.getStringExtra("patEgreso").toString())
        etDiagIngreso.setText(intent.getStringExtra("patIngreso").toString())
        etDireccion.setText(intent.getStringExtra("patDireccion").toString())
        etTelefono.setText(intent.getStringExtra("patTelefono").toString())
        etCorreo.setText(intent.getStringExtra("patCorreo").toString())
        etEstado.setText(intent.getStringExtra("patEstado").toString())
        etMunicipio.setText(intent.getStringExtra("patMunicipio").toString())
        etAlergias.setText(intent.getStringExtra("patAlergias").toString())
        etInstancia.setText(intent.getStringExtra("patInstancia").toString())
        etNacimiento.setText(intent.getStringExtra("patNacimiento").toString())


        mDialog.setTitle("Updating $etPatName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateEmpData(
                patId,
                etPatName.text.toString(),
                etPatFechaEgreso.text.toString(),
                etPatFechaIngreso.text.toString(),
                etDiagIngreso.text.toString(),
                etDiagIngreso.text.toString(),
                etDireccion.text.toString(),
                etTelefono.text.toString(),
                etCorreo.text.toString(),
                etEstado.text.toString(),
                etMunicipio.text.toString(),
                etAlergias.text.toString(),
                etNacimiento.text.toString(),
                etInstancia.text.toString()
            )

            Toast.makeText(applicationContext, "Patient Data Updated", Toast.LENGTH_LONG).show()

            //we are setting updated data to our textview
            tvPatNombre.text = etPatName.text.toString()
            tvPatFechaIngreso.text = etPatFechaIngreso.text.toString()
            tvPatFechaEgreso.text = etPatFechaEgreso.text.toString()
            tvPatDiagE.text = etDiagIngreso.text.toString()
            tvPatDiagI.text = etDiagEgreso.text.toString()
            tvPatDireccion.text = etDireccion.text.toString()
            tvPatTelefono.text = etTelefono.text.toString()
            tvPatCorreo.text = etCorreo.text.toString()
            tvPatEstado.text = etEstado.text.toString()
            tvPatMunicipio.text = etMunicipio.text.toString()
            tvPatAlergias.text = etAlergias.text.toString()
            tvPatNacimiento.text = etAlergias.text.toString()
            tvPatDInstancia.text = etInstancia.text.toString()

            alertDialog.dismiss()
        }
    }



    private fun updateEmpData(
        patId: String,
        patNombre: String,
        patIngreso: String,
        patEgreso: String,
        patEgresoFecha: String,
        patIngresoFecha: String,
        patDireccion: String,
        patTelefono: String,
        patCorreo: String,
        patEstado: String,
        patMunicipio: String,
        patAlergy: String,
        patNacimiento: String,
        patInstancia: String


        ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Patients").child(patId)
        val patInfo = PatientModel(patId, patNombre,  patIngreso, patEgreso, patDireccion, patEgresoFecha, patIngresoFecha, patTelefono, patCorreo, patEstado, patMunicipio, patAlergy, patNacimiento, patInstancia)
        dbRef.setValue(patInfo)
    }


    private fun initView() {
        tvPatId = findViewById(R.id.tvPatId)
        tvPatNombre = findViewById(R.id.tvPatName)
        tvPatFechaEgreso = findViewById(R.id.tvFechaEgreso)
        tvPatFechaIngreso = findViewById(R.id.tvPatFechaIngreso)
        tvPatDiagE = findViewById(R.id.tvPatDiagE)
        tvPatDiagI = findViewById(R.id.tvPatDiagI)
        tvPatAlergias = findViewById(R.id.tvPatAlergias)
        tvPatEstado = findViewById(R.id.tvPatEstado)
        tvPatNacimiento = findViewById(R.id.tvPatNacimiento)
        tvPatEstado = findViewById(R.id.tvPatEstado)
        tvPatMunicipio = findViewById(R.id.tvPatMunicipio)
        tvPatTelefono = findViewById(R.id.tvPatTelefono)
        tvPatDInstancia = findViewById(R.id.tvPatDInstancia)
        tvPatCorreo = findViewById(R.id.tvPatCorreo)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {
        tvPatId.text = intent.getStringExtra("patId")
        tvPatNombre.text = intent.getStringExtra("patNombre")
        tvPatFechaEgreso.text = intent.getStringExtra("patEgresoFecha")
        tvPatFechaIngreso.text = intent.getStringExtra("patIngresoFecha")
        tvPatDiagE.text = intent.getStringExtra("patEgreso")
        tvPatDiagI.text = intent.getStringExtra("patIngreso")
        tvPatAlergias.text = intent.getStringExtra("patAlergy")
        tvPatEstado.text = intent.getStringExtra("patEstado")
        tvPatNacimiento.text = intent.getStringExtra("patNacimiento")
        tvPatEstado.text = intent.getStringExtra("patEstado")
        tvPatMunicipio.text = intent.getStringExtra("patMunicipio")
        tvPatTelefono.text = intent.getStringExtra("patTelefono")
        tvPatDInstancia.text = intent.getStringExtra("patInstancia")
        tvPatCorreo.text = intent.getStringExtra("patCorreo")

    }

    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Patients").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Patient data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, FetchingPatients::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }
}