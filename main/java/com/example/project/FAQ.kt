package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FAQ : AppCompatActivity() {

    //declare-button Next
    private lateinit var btnN:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)

        //initialize the component same as layout
        //the component find via id was given through layout attribute
        this.btnN = findViewById<Button>(R.id.btnN)

        //event function
        btnN.setOnClickListener{
            //i = intent
            //to connect with other pages
            //button Next connect with about page
            val i = Intent(this,MainActivity::class.java)

            //run on the pages.Open session
            startActivity(i)
        }
    }
}
