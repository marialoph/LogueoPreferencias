package com.example.logueo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    private lateinit var btnLogin: Button
    private lateinit var textEmail: EditText
    private lateinit var textCont: EditText
    private lateinit var textUser: EditText

    private val MYUSER = "maria@gmail.com"
    private val MYPASS = "maria"
    private val USUARIO = "maria"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initEvent()
        logout()
    }

    private fun initEvent() {

        btnLogin = findViewById(R.id.btnLogin)
        textEmail = findViewById(R.id.email)
        textCont = findViewById(R.id.password)
        textUser = findViewById(R.id.user)

        btnLogin.setOnClickListener {
            val email = textEmail.text.toString()
            val password = textCont.text.toString()
            val user = textUser.text.toString()

            if (email == MYUSER && password == MYPASS && user==USUARIO) {
                loginPreferencias();
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(
                    this,
                    "No se puede acceder, correo o contraseña incorrecta",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }

    //Creamos una variable SharedPreferences para acceder a las preferencias compartidas
    //Editamos con sharedPref.edit() los datos, y los guardamos con putString el usuario
    //Se aplican los cambios con apply.
    private fun loginPreferencias() {
        val sharedPref = getSharedPreferences("preferencias logueo", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("user_email", MYUSER)
        editor.apply()
    }

    //Método que elimina todas las claves del fichero de preferencias compartidas.
    private fun logout() {
        val sharedPref = getSharedPreferences("preferencias logueo", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.clear()
        editor.apply()
    }


}