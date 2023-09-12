package com.dullgames.quizapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.dullgames.quizapp.R
import com.dullgames.quizapp.adapters.QuizAdapter
import com.dullgames.quizapp.models.Quiz
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: QuizAdapter
    private var quizList = mutableListOf<Quiz>()
    lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpViews()
    }

    private fun setUpViews() {
        setUpDrawer()
        setUpFireStore()
        setUpDatePicker()
        setUpRecyclerView()
    }

    private fun setUpDatePicker() {
        findViewById<FloatingActionButton>(R.id.date_fb).setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager, "DatePicker")
            datePicker.addOnPositiveButtonClickListener {
                val dateformat = SimpleDateFormat("dd-mm-yyyy")
                val date = dateformat.format(Date(it))
                val intent = Intent(this, QuestionActivity::class.java)
                intent.putExtra("Date", date)
                startActivity(intent)
            }
        }

    }


    private fun setUpFireStore() {
        firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("quizzes")
        collectionReference.addSnapshotListener { value, error ->
            if(value == null || error != null){
                Toast.makeText(this, "Error Fetching Data", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            Log.d("####", value.toObjects(Quiz::class.java).toString())
            quizList.clear()
            quizList.addAll(value.toObjects(Quiz::class.java))
            adapter.notifyDataSetChanged()
        }
    }

    private fun setUpDrawer() {
        val appBar = findViewById<Toolbar>(R.id.appBar)
        val drawer: DrawerLayout = findViewById(R.id.drawer_layout)
        setSupportActionBar(appBar)
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawer, R.string.app_name, R.string.app_name)
        actionBarDrawerToggle.syncState()
    }

    private fun setUpRecyclerView(){
        recyclerView = findViewById(R.id.quiz_recycler_view)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        adapter = QuizAdapter(this, quizList)
        recyclerView.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}