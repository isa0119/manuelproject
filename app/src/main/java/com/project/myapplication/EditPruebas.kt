package com.project.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class EditPruebas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_pruebas)
        val pruebatitulo = intent.getStringExtra("Prueba")
        val TextView:TextView = findViewById(R.id.bienvenida)
        TextView.setText(pruebatitulo)

    }
}