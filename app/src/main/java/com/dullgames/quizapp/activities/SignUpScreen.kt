package com.dullgames.quizapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.dullgames.quizapp.R
import com.google.firebase.auth.FirebaseAuth

class SignUpScreen : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_screen)
        firebaseAuth = FirebaseAuth.getInstance()
        findViewById<Button?>(R.id.buttonSignUp).setOnClickListener {
            signUpUser()
        }

        findViewById<TextView>(R.id.textview_login).setOnClickListener {
            val intent = Intent(this, LoginScreen::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun signUpUser(){
        val email: String = findViewById<EditText>(R.id.editText_email).text.toString()
        val pass: String = findViewById<EditText>(R.id.editText_password).text.toString()
        val confirm_pass: String = findViewById<EditText>(R.id.editText_confirm_password).text.toString()

        if(email.isBlank() || pass.isBlank() || confirm_pass.isBlank()){
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            return
        }

        if(pass != confirm_pass){
            Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this){
            if(it.isSuccessful){
                Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            }else{
                Toast.makeText(this, "Error creating user", Toast.LENGTH_SHORT).show()
            }
        }

    }
}