package com.example.pocketclinic.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pocketclinic.models.PatientModel
import com.example.pocketclinic.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

//Insertion Activity
class InsertPatients : AppCompatActivity(){

    private lateinit var etForm_name: EditText
    private lateinit var etForm_egresofecha: EditText
    private lateinit var etForm_alergy: EditText
    private lateinit var etForm_egreso: EditText
    private lateinit var etForm_ingreso: EditText
    private lateinit var etForm_instancia: EditText
    private lateinit var etForm_ingresofecha: EditText
    private lateinit var etForm_nacimiento: EditText
    private lateinit var etForm_estado: EditText
    private lateinit var etForm_municipio: EditText
    private lateinit var etForm_direccion: EditText
    private lateinit var etForm_correo: EditText
    private lateinit var etForm_telefono: EditText

    private lateinit var etSaveData: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_expedientes)

        etForm_name = findViewById(R.id.etForm_name)
        etForm_egresofecha = findViewById(R.id.etForm_egresofecha)
        etForm_alergy = findViewById(R.id.etForm_alergy)
        etForm_egreso = findViewById(R.id.etForm_egreso)
        etForm_ingreso = findViewById(R.id.etForm_ingreso)
        etForm_instancia = findViewById(R.id.etForm_instancia)
        etForm_ingresofecha = findViewById(R.id.etForm_ingresofecha)
        etForm_nacimiento = findViewById(R.id.etForm_nacimiento)
        etForm_estado = findViewById(R.id.etForm_estado)
        etForm_municipio = findViewById(R.id.etForm_municipio)
        etForm_direccion = findViewById(R.id.etForm_direccion)
        etForm_correo = findViewById(R.id.etForm_correo)
        etForm_telefono = findViewById(R.id.etForm_telefono)
        etSaveData = findViewById(R.id.etSaveData)


        dbRef = FirebaseDatabase.getInstance().getReference("Patients")

        etSaveData.setOnClickListener {
            savePatientData()
        }
    }

    private fun savePatientData() {

        //getting values
        val patNombre = etForm_name.text.toString()
        val patEgresoFecha = etForm_egresofecha.text.toString()
        val patAlergy = etForm_alergy.text.toString()
        val patEgreso = etForm_egreso.text.toString()
        val patIngreso = etForm_ingreso.text.toString()
        val patInstancia = etForm_instancia.text.toString()
        val patIngresoFecha = etForm_ingresofecha.text.toString()
        val patNacimiento = etForm_nacimiento.text.toString()
        val patEstado = etForm_estado.text.toString()
        val patDireccion = etForm_direccion.text.toString()
        val patMunicipio = etForm_municipio.text.toString()
        val patTelefono = etForm_telefono.text.toString()
        val patCorreo = etForm_correo.text.toString()


        if (patNombre.isEmpty()) {
            etForm_name.error = "Please fill in the field"
        }
        if (patEgresoFecha.isEmpty()) {
            etForm_egresofecha.error = "Please fill in the field"
        }
        if (patAlergy.isEmpty()) {
            etForm_alergy.error = "Please fill in the field"
        }
        if (patEgreso.isEmpty()) {
            etForm_egreso.error = "Please fill in the field"
        }
        if (patIngreso.isEmpty()) {
            etForm_ingreso.error = "Please fill in the field"
        }
        if (patInstancia.isEmpty()) {
            etForm_instancia.error = "Please fill in the field"
        }
        if (patIngresoFecha.isEmpty()) {
            etForm_ingresofecha.error = "Please fill in the field"
        }
        if (patNacimiento.isEmpty()) {
            etForm_nacimiento.error = "Please fill in the field"
        }
        if (patEstado.isEmpty()) {
            etForm_estado.error = "Please fill in the field"
        }
        if (patDireccion.isEmpty()) {
            etForm_direccion.error = "Please fill in the field"
        }
        if (patMunicipio.isEmpty()) {
            etForm_municipio.error = "Please fill in the field"
        }
        if (patTelefono.isEmpty()) {
            etForm_telefono.error = "Please fill in the field"
        }
        if (patCorreo.isEmpty()) {
            etForm_correo.error = "Please fill in the field"
        }



        val patId = dbRef.push().key!!

        val patient = PatientModel(patId, patNombre, patEgresoFecha, patAlergy, patEgreso, patIngreso, patInstancia, patIngresoFecha, patNacimiento
        ,patEstado, patDireccion, patMunicipio, patTelefono, patCorreo)

        dbRef.child(patId).setValue(patient)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                etForm_name.text.clear()
                etForm_alergy.text.clear()
                etForm_correo.text.clear()
                etForm_municipio.text.clear()
                etForm_estado.text.clear()
                etForm_nacimiento.text.clear()
                etForm_ingresofecha.text.clear()
                etForm_instancia.text.clear()
                etForm_ingreso.text.clear()
                etForm_egreso.text.clear()
                etForm_egresofecha.text.clear()
                etForm_direccion.text.clear()
                etForm_telefono.text.clear()

            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }
}