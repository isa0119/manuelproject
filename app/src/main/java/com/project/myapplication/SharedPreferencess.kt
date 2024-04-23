package com.project.myapplication

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencess(context: Context) {
    private val SP : SharedPreferences = context.getSharedPreferences("controldesesiones", Context.MODE_PRIVATE)
    private val editor:  SharedPreferences.Editor = SP.edit()
    companion object {
        private const val KEY_IS_LOGGED_IN = "isLoggedIn"
        private const val KEY_USER = "user"
        private const val KEY_USER_ID = "iduser"
    }
    var isLoggedIn: Boolean
        get() = SP.getBoolean(KEY_IS_LOGGED_IN, false)
        set(value) = editor.putBoolean(KEY_IS_LOGGED_IN, value).apply()
    var user: String?
        get() = SP.getString(KEY_USER, "sin usuario")
        set(value) = editor.putString(KEY_USER, value).apply()
    var iduser: Int
        get() = SP.getInt(KEY_USER_ID, -1)
        set(value) = editor.putInt(KEY_USER_ID, value).apply()
    fun CerrarSesion(){
        editor.clear().apply()
    }
}