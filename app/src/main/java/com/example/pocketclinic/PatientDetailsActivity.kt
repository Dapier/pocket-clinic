package com.example.pocketclinic

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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
        PatId: String,
        patName: String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog, null)

        mDialog.setView(mDialogView)

        val etPatName = mDialogView.findViewById<EditText>(R.id.form_name)
        val etFechaEgreso = mDialogView.findViewById<EditText>(R.id.form_egresofecha)
        val etFechaIngreso = mDialogView.findViewById<EditText>(R.id.form_ingresofecha)
        val etAlergia = mDialogView.findViewById<EditText>(R.id.form_alergy)
        val etDiagEgreso = mDialogView.findViewById<EditText>(R.id.form_egreso)
        val etDiagIngreso = mDialogView.findViewById<EditText>(R.id.form_ingreso)
        val etInstancia = mDialogView.findViewById<EditText>(R.id.form_instancia)
        val etEstado = mDialogView.findViewById<EditText>(R.id.form_estado)
        val etMunicipio = mDialogView.findViewById<EditText>(R.id.form_municipio)
        val etDireccion = mDialogView.findViewById<EditText>(R.id.form_direccion)
        val etNacimiento = mDialogView.findViewById<EditText>(R.id.form_nacimiento)
        val etTelefono = mDialogView.findViewById<EditText>(R.id.form_telefono)
        val etCorreo = mDialogView.findViewById<EditText>(R.id.form_correo)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etPatName.setText(intent.getStringExtra("PatName").toString())
        etFechaEgreso.setText(intent.getStringExtra("PatFechaEgreso").toString())
        etFechaIngreso.setText(intent.getStringExtra("PatFechaIngreso").toString())
        etAlergia.setText(intent.getStringExtra("patAlergia").toString())
        etDiagEgreso.setText(intent.getStringExtra("PatDiagEgreso").toString())
        etDiagIngreso.setText(intent.getStringExtra("PatDiagIngreso").toString())
        etInstancia.setText(intent.getStringExtra("PatInstancia").toString())
        etEstado.setText(intent.getStringExtra("PatEstado").toString())
        etMunicipio.setText(intent.getStringExtra("PatMunicipio").toString())
        etDireccion.setText(intent.getStringExtra("PatDireccion").toString())
        etNacimiento.setText(intent.getStringExtra("PatNacimiento").toString())
        etTelefono.setText(intent.getStringExtra("PatTelefono").toString())
        etCorreo.setText(intent.getStringExtra("PatCorreo").toString())


        mDialog.setTitle("Updating $etPatName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateEmpData(
                PatId,
                etPatName.text.toString(),
                etFechaEgreso.text.toString(),
                etFechaIngreso.text.toString(),
                etAlergia.text.toString(),
                etDiagEgreso.text.toString(),
                etDiagIngreso.text.toString(),
                etInstancia.text.toString(),
                etEstado.text.toString(),
                etMunicipio.text.toString(),
                etDireccion.text.toString(),
                etNacimiento.text.toString(),
                etTelefono.text.toString(),
                etCorreo.text.toString()
            )

            Toast.makeText(applicationContext, "Patient Data Updated", Toast.LENGTH_LONG).show()

            //we are setting updated data to our textviews
            tvPatNombre.text = etPatName.text.toString()
            tvPatFechaEgreso.text = etFechaEgreso.text.toString()
            tvPatFechaIngreso.text = etDiagIngreso.text.toString()
            tvPatAlergias.text = etAlergia.text.toString()
            tvPatDiagI.text = etDiagIngreso.text.toString()
            tvPatDiagE.text = etDiagEgreso.text.toString()
            tvPatDInstancia.text = etInstancia.text.toString()
            tvPatEstado.text = etEstado.text.toString()
            tvPatMunicipio.text = etMunicipio.text.toString()
            tvPatNacimiento.text = etNacimiento.text.toString()
            tvPatTelefono.text = etTelefono.text.toString()
            tvPatCorreo.text = etCorreo.text.toString()

            alertDialog.dismiss()
        }
    }

    private fun updateEmpData(
        id: String,
        name: String,
        age: String,
        salary: String,
        toString: String,
        toString1: String,
        toString2: String,
        toString3: String,
        toString4: String,
        toString5: String,
        toString6: String,
        toString7: String,
        toString8: String,
        toString9: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Patients").child(id)
        //val patInfo = PatientModel(tvPatId, tvPatNombre, tvPatEstado, tvPatTelefono)
        //dbRef.setValue(patInfo)
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
        tvPatFechaEgreso.text = intent.getStringExtra("patFechaEgreso")
        tvPatFechaIngreso.text = intent.getStringExtra("patFechaIngreso")
        tvPatDiagE.text = intent.getStringExtra("patDiagE")
        tvPatDiagI.text = intent.getStringExtra("patDiagI")
        tvPatAlergias.text = intent.getStringExtra("patAlergias")
        tvPatEstado.text = intent.getStringExtra("patEstado")
        tvPatNacimiento.text = intent.getStringExtra("patNacimiento")
        tvPatEstado.text = intent.getStringExtra("patEstado")
        tvPatMunicipio.text = intent.getStringExtra("patMunicipio")
        tvPatTelefono.text = intent.getStringExtra("patTelefono")
        tvPatDInstancia.text = intent.getStringExtra("patDinstancia")
        tvPatCorreo.text = intent.getStringExtra("patCorreo")

    }

    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Patients").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Patient data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, Registrarexpedientes::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }
}