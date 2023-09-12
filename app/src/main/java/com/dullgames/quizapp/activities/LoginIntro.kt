package com.dullgames.quizapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.dullgames.quizapp.R
import com.google.firebase.auth.FirebaseAuth

class LoginIntro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_intro)
        val firebaseAuth = FirebaseAuth.getInstance()

        findViewById<Button>(R.id.button_get_started).setOnClickListener{
            if(firebaseAuth.currentUser != null){
                Toast.makeText(this, "User is already logged In", Toast.LENGTH_SHORT).show()
                getStarted("MAIN")
            }else{
                getStarted("LOGIN")
            }

        }

    }

    private fun getStarted(option: String){
        val intent:Intent = when(option){
            "MAIN" -> Intent(this, MainActivity::class.java)
            "LOGIN" -> Intent(this, LoginScreen::class.java)
            else -> throw Exception("No path exists!")
        }
        startActivity(intent)
        finish()
    }
}