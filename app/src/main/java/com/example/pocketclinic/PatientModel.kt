package com.example.pocketclinic

import android.widget.EditText
import android.widget.TextView

data class PatientModel(
    var patId: String? = null,
    var patNombre: String? = null,
    var patEgresoFecha: String? = null,
    var patAlergy: String? = null,
    var patEgreso: String? = null,
    var patIngreso: String? = null,
    var patInstancia: String? = null,
    var patIngresoFecha: String? = null,
    var patNacimiento: String? = null,
    var patEstado: String? = null,
    var patDireccion: String? = null,
    var patMunicipio: String? = null,
    val patTelefono: String? = null,
    val patCorreo: String? = null
)

