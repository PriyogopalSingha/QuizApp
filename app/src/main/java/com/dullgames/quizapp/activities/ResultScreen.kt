package com.dullgames.quizapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.widget.TextView
import com.dullgames.quizapp.R
import com.dullgames.quizapp.models.Quiz
import com.google.gson.Gson

class ResultScreen : AppCompatActivity() {
    lateinit var quiz: Quiz
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_screen)
        setUpViews()
    }

    private fun setUpViews() {
        val quizData = intent.getStringExtra("FINAL")
        quiz = Gson().fromJson(quizData, Quiz::class.java)

        calculateScore()
        setAnswerView()
    }

    private fun setAnswerView() {
        val builder = StringBuilder("")
        for(entry in quiz.questions.entries){
            val question = entry.value
            builder.append("<font color'#18206F'><b>Question: ${question.description}</b></font><br/><br/>")
            builder.append("<font color'#009688'>Answer: ${question.answer}</font><br/><br/>")
        }
        findViewById<TextView>(R.id.txtAnswer).text = Html.fromHtml(builder.toString())
    }

    private fun calculateScore() {
        var score = 0
        for(entry in quiz.questions.entries){
            val question = entry.value
            if(question.answer == question.userAnswer){
                score += 10
            }
        }
        findViewById<TextView>(R.id.txtScore).text = "Your Score is : $score"
    }
}