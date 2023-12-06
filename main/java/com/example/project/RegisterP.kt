package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterP : AppCompatActivity() {
    private lateinit var dbRef : DatabaseReference
    private lateinit var btnAdd : Button
    private lateinit var btnReset : Button
    private lateinit var name: EditText
    private lateinit var pNo : EditText
    private lateinit var loanAm : EditText
    private lateinit var loanDur : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_p)

        //declare the component
        btnAdd = findViewById<Button>(R.id.btnAdd)
        btnReset = findViewById<Button>(R.id.btnReset)
        name = findViewById<EditText>(R.id.name)
        pNo = findViewById<EditText>(R.id.pNo)
        loanAm = findViewById<EditText>(R.id.loanAm)
        loanDur = findViewById<EditText>(R.id.loanDur)
        //popup message when click button register
        Toast.makeText(this, "Register page", Toast.LENGTH_LONG).show()

        btnAdd.setOnClickListener{
            //parameter - change the input data to string
            saveUserData(name.text.toString(),pNo.text.toString(),loanAm.text.toString(),loanDur.text.toString())
        }

        btnReset.setOnClickListener {
            name.setText(" ")
            pNo.setText(" ")
            loanAm.setText(" ")
            loanDur.setText(" ")
        }
    }

    private fun saveUserData(n:String, p:String, a:String, d:String)
    {
        dbRef = FirebaseDatabase.getInstance().getReference("User")

        val userID = dbRef.push().key!!

        val UD = Model(userID, n, p, a, d)

        //setting to push data inside table
        dbRef.child(userID).setValue(UD)

            //success record it will pop up success
            .addOnCompleteListener{
                Toast.makeText(this,"Successfully added!", Toast.LENGTH_LONG).show()
                //fail to record it will popup failure
            }.addOnFailureListener{
                Toast.makeText(this,"Failed to add", Toast.LENGTH_LONG).show()
            }

        //declare variable i to connect to next pages/ activity
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}