package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.text.DecimalFormat

class Calculate : AppCompatActivity() {

    //declare component that will use
    //lateinit - never initialize the button btn1 - btnMonthly
    lateinit var b1 : Button
    //lateinit - never initialize the button btn1 - btnYearly
    lateinit var b2 : Button
    //lateinit - never initialize the button btn1 - btnReset
    lateinit var b3 : Button
    //lateinit - never initialize the textview - tvStatus
    lateinit var t1 : TextView
    //lateinit - never initialize the EditText "editTextLoan
    lateinit var loan : EditText
    //lateinit - never initialize the EditText "editTextDuration
    lateinit var duration : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculate)

        b1 = findViewById<Button>(R.id.btnMonthly)
        b2 = findViewById<Button>(R.id.btnYearly)
        b3 = findViewById<Button>(R.id.btnReset)
        t1 = findViewById<TextView>(R.id.tvStatus)
        loan = findViewById<EditText>(R.id.editTextLoan)
        duration = findViewById<EditText>(R.id.editTextDuration)

        //initialize function decimal
        val df = DecimalFormat("#,###,###.##")

        //set function for button Monthly
        b1.setOnClickListener {
            var loanVal: Double = loan.text.toString().toDouble()
            var durationVal: Double = duration.text.toString().toDouble()

            //durationVal * 12
            var mPay = ((loanVal * 0.035) + loanVal) / (durationVal * 12)

            t1.text = "Monthly payment RM" + df.format(mPay).toString()

        }

        //set function for button Yearly
        b2.setOnClickListener {
            var loanVal: Double = loan.text.toString().toDouble()
            var durationVal: Double = duration.text.toString().toDouble()

            //durationVal * 12
            var yPay = ((loanVal * 0.035) + loanVal) / durationVal

            t1.text = "Yearly payment RM" + df.format(yPay).toString()

        }

        //set function for button Reset
        b3.setOnClickListener {
            //to reset all amount
            loan.setText("")
            duration.setText("")

            t1.text = ""
        }
    }
}