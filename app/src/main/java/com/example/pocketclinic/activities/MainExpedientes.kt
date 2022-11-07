package com.example.pocketclinic.activities

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.example.pocketclinic.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainExpedientes : AppCompatActivity() {
    private lateinit var btnRegistrarDatos: Button
    private lateinit var btnBuscarDatos: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.opciones_expedientes)

        btnRegistrarDatos = findViewById(R.id.btnRegistrarDatos)
        btnBuscarDatos = findViewById(R.id.btnBuscarDatos)

        btnRegistrarDatos.setOnClickListener {
            val intent = Intent(this, InsertPatients::class.java)
            startActivity(intent)
        }

        btnBuscarDatos.setOnClickListener {
            val intent = Intent(this, FetchingPatients::class.java)
            startActivity(intent)
        }

        val firebase: DatabaseReference = FirebaseDatabase.getInstance().getReference()
    }
}