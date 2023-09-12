package com.dullgames.quizapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.dullgames.quizapp.R
import com.google.firebase.auth.FirebaseAuth

class LoginScreen : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        firebaseAuth = FirebaseAuth.getInstance()
        findViewById<Button>(R.id.buttonLogin).setOnClickListener{
            loginUser()
        }
        findViewById<TextView>(R.id.textview_sign_up).setOnClickListener{
            val intent = Intent(this, SignUpScreen::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loginUser(){
        val email = findViewById<EditText>(R.id.editText_login_email).text.toString().trim()
        val password = findViewById<EditText>(R.id.editText_login_password).text.toString().trim()

        if(email.isBlank() || password.isBlank()){
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){
            if(it.isSuccessful){
                Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this, ""+it.exception, Toast.LENGTH_SHORT).show()
                Log.d("####",""+it.exception)
            }

        }
    }

}