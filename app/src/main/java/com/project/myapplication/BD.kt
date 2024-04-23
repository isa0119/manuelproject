package com.project.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BD(private val context: Context): SQLiteOpenHelper(context, DBNAME, null, DB_VERSION) {
    companion object{
        private const val DBNAME = "Cursos_Gratis.db"
        private const val DB_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val users = ("CREATE TABLE usuarios("+
                "iduser INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "user TEXT, "+
                "pass TEXT)")
        db?.execSQL(users)
        val Cursos = ("CREATE TABLE Cursos(" +
                "idcurso INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT, "+
                "contenido TEXT)")
        db?.execSQL(Cursos)
        val Pruebas = ("CREATE TABLE Prueba(" +
                "idprueba INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT, "+
                "idcurso INTEGER, " +
                "FOREIGN KEY(idcurso) REFERENCES Cursos(idcurso) ON DELETE CASCADE)")
        db?.execSQL(Pruebas)
        val Preguntas = ("CREATE TABLE Preguntas(" +
                "idpregunta INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "pregunta TEXT, " +
                "respuesta TEXT, " +
                "opcion1 TEXT, " +
                "opcion2 TEXT, " +
                "opcion3 TEXT, " +
                "idprueba INTEGER, " +
                "FOREIGN KEY(idprueba) REFERENCES Prueba(idprueba) ON DELETE CASCADE)")
        db?.execSQL(Preguntas)
        val cursos_usuario = ("CREATE TABLE cursos_usuario(" +
                "idcurso INTEGER, " +
                "iduser INTEGER, " +
                "FOREIGN KEY(iduser) REFERENCES usuarios(iduser) ON DELETE CASCADE, "  +
                "FOREIGN KEY(idcurso) REFERENCES Cursos(idcurso) ON DELETE CASCADE)")
        db?.execSQL(cursos_usuario)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val users = "DROP TABLE IF EXISTS usuarios"
        db?.execSQL(users)
        val Cursos = "DROP TABLE IF EXISTS Cursos"
        db?.execSQL(Cursos)
        val Pruebas = "DROP TABLE IF EXISTS Prueba"
        db?.execSQL(Pruebas)
        val Preguntas = "DROP TABLE IF EXISTS Preguntas"
        db?.execSQL(Preguntas)
        val cursos_usuario = "DROP TABLE IF EXISTS cursos_usuario"
        db?.execSQL(cursos_usuario)
        onCreate(db)
    }
    fun loginctrl (name:String, pass:String):Boolean{
        val arr = arrayOf(name, pass)
        val query = readableDatabase.query("usuarios", null, "user = ? AND pass = ?", arr, null, null, null)
        val user = query.count>0
        query.close()
        return user

    }
    fun exist(name:String):Boolean{
        val name = arrayOf(name)
        val query = readableDatabase.query("usuarios", null, "user = ?", name, null, null, null)
        val user = query.count>0
        query.close()
        return user
    }
    fun cursoexist(name:String):Boolean{
        val name = arrayOf(name)
        val query = readableDatabase.query("Cursos", null, "nombre = ?", name, null, null, null)
        val user = query.count>0
        query.close()
        return user
    }
    fun nuevousuario(name: String, pass: String): Long{
        val valores = ContentValues().apply {
            put("user", name)
            put("pass", pass)
        }
        return writableDatabase.insert("usuarios", null, valores)
    }
    fun nuevocurso(name: String, contenido: String): Long{
        val valores = ContentValues().apply {
            put("nombre", name)
            put("contenido", contenido)
        }
        return writableDatabase.insert("Cursos", null, valores)
    }
    fun nuevaPrueba(IDcurso:Int, Titulo:String):Long{
        val valores = ContentValues().apply {
            put("nombre", Titulo)
            put("idcurso", IDcurso)
        }
        return writableDatabase.insert("Prueba", null, valores)
    }
    fun nuevaPregunta(IDPrueba: Int, Pregunta:String, Respuesta:String, opcion1:String, opcion2:String, opcion3:String):Long{
        val valores = ContentValues().apply {
            put("idprueba", IDPrueba)
            put("pregunta", Pregunta)
            put("respuesta", Respuesta)
            put("opcion1", opcion1)
            put("opcion2", opcion2)
            put("opcion3", opcion3)
        }
        return  writableDatabase.insert("Preguntas", null, valores)
    }
    fun getiduser (name:String, pass:String): Cursor?{
        val arr = arrayOf(name, pass)
        val queryid = readableDatabase.query("usuarios", arrayOf("iduser"),"user = ? AND pass = ?", arr,null, null, null )
        return queryid
    }
    fun getPreguntas (IDPrueba:Int):Cursor?{
        val values = arrayOf(IDPrueba.toString())
        val cursor = readableDatabase.query("Preguntas", null, "idprueba = ?", values, null, null, null )
        return cursor
    }
    fun getCursos ():Cursor? {
        return readableDatabase.query("Cursos", null, null, null, null, null, null)
    }
    fun getCurso (IDcurso: Int):Cursor?{
        val values = arrayOf(IDcurso.toString())
        val cursor = readableDatabase.query("Cursos", null, "idcurso = ?", values, null, null, null)
        return cursor
    }
    fun getPrueba(IDcurso: Int):Cursor?{
        val values = arrayOf(IDcurso.toString())
        val cursor = readableDatabase.query("Prueba", null, "idcurso = ?", values, null, null, null)
        return cursor
    }
    fun eliminarCurso(id: Int): Boolean {
        val query = writableDatabase.delete("Cursos", "idcurso = ?", arrayOf(id.toString()))
        return query > 0
    }
    fun actualizarCurso(IDcurso: Int, Titulo: String, contenido: String):Boolean{
        val valoresActualizados = ContentValues().apply {
            put("nombre", Titulo)
            put("contenido", contenido)
        }
        val arr = arrayOf(IDcurso.toString())
        val filasActualizadas = writableDatabase.update("Cursos", valoresActualizados, "idcurso = ?", arr)
        return filasActualizadas > 0
    }

}