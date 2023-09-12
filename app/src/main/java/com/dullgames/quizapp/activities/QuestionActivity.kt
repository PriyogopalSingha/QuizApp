package com.dullgames.quizapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dullgames.quizapp.R
import com.dullgames.quizapp.adapters.QuestionAdapter
import com.dullgames.quizapp.models.Question
import com.dullgames.quizapp.models.Quiz
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson

class QuestionActivity : AppCompatActivity() {

    var quizzes: MutableList<Quiz>? = null
    var questions: MutableMap<String, Question>? = null
    var index: Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        setUpFireStore()
        setUpEventListeners()
    }

    private fun setUpEventListeners() {
        val prevBtn = findViewById<Button>(R.id.prevBtn)
        val nextBtn = findViewById<Button>(R.id.nextBtn)
        val submitBtn = findViewById<Button>(R.id.submitBtn)
        prevBtn.setOnClickListener {
            index--
            setUpViews()
        }
        nextBtn.setOnClickListener {
            index++
            setUpViews()
        }
        submitBtn.setOnClickListener {
            val intent = Intent(this, ResultScreen::class.java)
            val json = Gson().toJson(quizzes!![0])
            intent.putExtra("FINAL",json)
            startActivity(intent)
        }
    }

    private fun setUpFireStore() {
        val firestore = FirebaseFirestore.getInstance()
        val date = intent.getStringExtra("DATE")
        if(!date.isNullOrEmpty()){
            firestore.collection("quizzes").whereEqualTo("title",date)
                .get()
                .addOnSuccessListener {
                    if(it != null){
                        quizzes = it.toObjects(Quiz::class.java)
                        questions = quizzes!![0].questions
                        setUpViews()
                    }
                }
        }

    }

    private fun setUpViews() {
        val prevBtn = findViewById<Button>(R.id.prevBtn)
        prevBtn.visibility = View.GONE
        val nextBtn = findViewById<Button>(R.id.nextBtn)
        nextBtn.visibility = View.GONE
        val submitBtn = findViewById<Button>(R.id.submitBtn)
        submitBtn.visibility = View.GONE
        if(index == 1){
            nextBtn.visibility = View.VISIBLE
        }else if(index == questions?.size){
            submitBtn.visibility = View.VISIBLE
            prevBtn.visibility = View.VISIBLE
        }else{
            nextBtn.visibility = View.VISIBLE
            prevBtn.visibility = View.VISIBLE
        }
        val question = questions!!["question$index"]
        question?.let{
            val adapter = QuestionAdapter(this, it)
            val optionsView: RecyclerView = findViewById<RecyclerView>(R.id.recyclerview_options)
            findViewById<TextView>(R.id.description_textView).text = it.description
            optionsView.layoutManager = LinearLayoutManager(this)
            optionsView.adapter = adapter
        }

    }
}