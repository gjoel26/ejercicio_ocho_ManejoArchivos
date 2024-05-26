package com.example.ejercicio_ocho

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson

class EditActivity : AppCompatActivity() {

    var position: Int = 0
    lateinit var txtName: EditText
    lateinit var txtPhoneNumber: EditText
    lateinit var fileHandling: FileHandling

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        val txtTitle = findViewById<TextView>(R.id.txtTitle)
        txtTitle.text = "Modificar Contacto"
        position = intent.getIntExtra("position", -1)
        Log.e("Contact", "Se recibió un ${position}")
        txtName = findViewById(R.id.txtName)
        txtPhoneNumber = findViewById(R.id.txtPhoneNomber)
        fileHandling = FileHandling(this)

        val contact = ProvicionalData.listContact[position]
        txtName.setText(contact.name)
        txtPhoneNumber.setText(contact.phoneNumber)
    }

    fun save(v: View) {
        val contact = Contact(txtName.text.toString(), txtPhoneNumber.text.toString())
        ProvicionalData.listContact[position] = contact

        // Guardar los contactos después de modificar uno
        val contactsJson = Gson().toJson(ProvicionalData.listContact)
        fileHandling.save(contactsJson)

        Toast.makeText(this, "Modificado", Toast.LENGTH_LONG).show()
        finish()
    }
}
