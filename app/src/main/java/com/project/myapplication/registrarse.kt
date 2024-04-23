package com.project.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class registrarse : AppCompatActivity() {
    private lateinit var DB:BD
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)
        DB=BD(this)

        val usuario:EditText = findViewById(R.id.usuario)
        val contraseña:EditText = findViewById(R.id.contraseña)
        val btnRegistrarse:Button = findViewById(R.id.registrarse)

        btnRegistrarse.setOnClickListener {
            if (usuario.text.isNotEmpty() && contraseña.text.isNotEmpty()){
                Validar(usuario.text.toString(), contraseña.text.toString())
            }else{
                Toast.makeText(this, "por favor, rellena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun Validar(usuario:String, contraseña:String){
        if(DB.exist(usuario)){
            Toast.makeText(this, "este nombre de usuario ya esta en uso", Toast.LENGTH_SHORT).show()
        }else{
            if (DB.nuevousuario(usuario, contraseña) != -1L ){
                Toast.makeText(this, "registro exitoso", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else{
                Toast.makeText(this, "error al registrar", Toast.LENGTH_SHORT).show()
            }
        }
    }
}