package com.project.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var DB:BD
    private lateinit var SP:SharedPreferencess
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DB=BD(this)
        SP=SharedPreferencess(this)

        val usuario:EditText = findViewById(R.id.usuario)
        val contraseña:EditText = findViewById(R.id.contraseña)
        val btningresar:Button = findViewById(R.id.ingresar)
        val btnregistrarse:Button = findViewById(R.id.registrarse)
        if (SP.isLoggedIn == true){
            when(SP.user){
                ".ADMIN" ->{
                    startActivity(Intent(this, admin_main::class.java))
                    finish()
                }
                else ->{
                    startActivity(Intent(this, user_main::class.java))
                    finish()
                }
            }
        }
        btningresar.setOnClickListener {
            if (usuario.text.isNotEmpty() && contraseña.text.isNotEmpty()){
                Login(usuario.text.toString(), contraseña.text.toString())
            }else{
                Toast.makeText(this, "por favor, rellene todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
        btnregistrarse.setOnClickListener {
            startActivity(Intent(this, registrarse::class.java))
            finish()
        }
    }
    fun Login(usuario:String, contraseña:String){
        if (usuario == ".ADMIN"){
            SP.user = usuario
            SP.isLoggedIn = true
            startActivity(Intent(this, admin_main::class.java))
            finish()
        }else{
            val query = DB.loginctrl(usuario, contraseña)
            if (query){
                val id = DB.getiduser(usuario, contraseña)
                if(id != null && id.moveToFirst()){
                    val rows = id.getColumnIndex("iduser")
                    if (rows != -1) {
                        var userId = id.getInt(rows)
                        SP.iduser = userId
                        SP.user = usuario
                        SP.isLoggedIn = true
                        startActivity(Intent(this, user_main::class.java))
                        finish()
                    }else{
                        Toast.makeText(this, "no se encontro resultado", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this, "No existe usuario", Toast.LENGTH_SHORT).show()
            }
        }
    }
}