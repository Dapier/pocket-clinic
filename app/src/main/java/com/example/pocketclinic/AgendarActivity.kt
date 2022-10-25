package com.example.pocketclinic

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.*
import com.example.pocketclinic.databinding.AgendarCitaBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*


class AgendarActivity :AppCompatActivity() {
    private lateinit var tvDatePicker: TextView
    private lateinit var botonFecha:Button
    private lateinit var tvTime: TextView
    private lateinit var btnTimePicker:Button
    private  lateinit var binding: AgendarCitaBinding
    private lateinit var database:DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = AgendarCitaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.agendar_cita)



        tvDatePicker = findViewById(R.id.tvFecha)
        botonFecha = findViewById(R.id.botonFecha)

        tvTime = findViewById(R.id.tvHora)
        btnTimePicker = findViewById(R.id.botonHora)

        val myCalendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR,year)
            myCalendar.set(Calendar.MONTH,month)
            myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            updatelable(myCalendar)
        }

        botonFecha.setOnClickListener{
            DatePickerDialog( this, datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }


        btnTimePicker.setOnClickListener {
            val currentTime = Calendar.getInstance()
            val startHour = currentTime.get(Calendar.HOUR_OF_DAY)
            val startMinute =currentTime.get(Calendar.MINUTE)

            TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                tvTime.setText("$hourOfDay:$minute")

            },startHour,startMinute,false).show()
        }

        //Spinner de elementos
        val spinner = findViewById<Spinner>(R.id.spnElementos)

        val lista = resources.getStringArray(R.array.opciones)

        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item,lista)
        spinner.adapter=adaptador

        spinner.onItemSelectedListener = object:
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                p0: AdapterView<*>?,
                p1: View?,
                p2: Int,
                p3: Long
            ) {

                //Mensaje que aparece en la parte inferior de la pantalla
                Toast.makeText(this@AgendarActivity,lista[p2],Toast.LENGTH_LONG).show()
                val selectionService = p2
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }


        /*val botonGenerar = findViewById<Button>(R.id.btnGenerarCita)*/

        binding.btnGenerarCita.setOnClickListener {

            val Name = binding.name.text.toString()
            val service = spinner.onItemSelectedListener.toString()
            val date = tvDatePicker.toString()
            val hour = tvTime.toString()

            database = FirebaseDatabase.getInstance().getReference("Citas")
            val Citas = citationData(Name,date,hour,service)
            database.child(Name).setValue(Citas).addOnSuccessListener {
                binding.name.text.clear()

                Toast.makeText(this,"Cita Agendada",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this,"ERROR",Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun updatelable(myCalendar: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        tvDatePicker.setText(sdf.format(myCalendar.time))

    }



}