package com.example.pocketclinic

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.pocketclinic.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var btnRegistrarDatos: Button
    private lateinit var btnBuscarDatos: Button
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_profile, R.id.navigation_messages
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        btnRegistrarDatos = findViewById(R.id.btnRegistrarDatos)
        btnBuscarDatos = findViewById(R.id.btnBuscarDatos)

        btnRegistrarDatos.setOnClickListener {
            val intent = Intent(this, Registrarexpedientes::class.java)
            startActivity(intent)
        }

        btnBuscarDatos.setOnClickListener {
            val intent = Intent(this, Buscarexpedientes::class.java)
            startActivity(intent)
        }

        val firebase : DatabaseReference = FirebaseDatabase.getInstance().getReference()
    }
}