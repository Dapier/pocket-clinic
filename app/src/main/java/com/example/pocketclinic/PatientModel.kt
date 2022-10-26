package com.example.pocketclinic

import android.widget.EditText
import android.widget.TextView

data class PatientModel(
    var patId: String,
    var form_name: String,
    var form_egresofecha: String,
    var form_alergy: String,
    var form_instancia: String? = null,
    var form_ingreso: String? = null,
    var form_nacimiento: String? = null,
    var form_telefono: String? = null,
    var form_municipio: String? = null,
    var form_direccion: String? = null,
    var form_estado: String? = null,
    var form_egreso: String? = null,
    val form_correo: String? = null
) {
    val form_ingresofecha: String? = null
}
