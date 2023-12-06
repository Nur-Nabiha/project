package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var btnReg: Button
    private lateinit var btnRec : Button
    private lateinit var btnC : Button
    private lateinit var btnFAQ : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialize all button
        btnReg = findViewById<Button>(R.id.btnReg)
        btnRec = findViewById<Button>(R.id.btnRec)
        btnC = findViewById<Button>(R.id.btnCalc)
        btnFAQ = findViewById<Button>(R.id.btnFAQ)

        //function press button for button add record
        btnReg.setOnClickListener {
            //declare variable i to connect to next pages/activity = MainActivity2
            val i = Intent(this, RegisterP::class.java)
            startActivity(i)
        }
        btnRec.setOnClickListener{
            //declare variable i to connect to next pages/ activity = MainActivity 3
            val i = Intent (this, RecordHistory::class.java)
            startActivity(i)
        }
        btnC.setOnClickListener{
            //declare variable i to connect to next pages/ activity = MainActivity 3
            val i = Intent (this, Calculate::class.java)
            startActivity(i)
        }
        btnFAQ.setOnClickListener{
            //declare variable i to connect to next pages/ activity = MainActivity 3
            val i = Intent (this, FAQ::class.java)
            startActivity(i)
        }
    }
}