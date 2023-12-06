package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase


class UserDetailsAct : AppCompatActivity() {
    private lateinit var tvUserId: TextView
    private lateinit var tvUserName: TextView
    private lateinit var tvUserPNo: TextView
    private lateinit var tvUserLoanAmt: TextView
    private lateinit var tvUserLoanDur: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        tvUserId = findViewById(R.id.tvUserId)
        tvUserName = findViewById(R.id.tvUserName)
        tvUserPNo = findViewById(R.id.tvUserPNo)
        tvUserLoanAmt = findViewById(R.id.tvUserLoanAmt)
        tvUserLoanDur = findViewById(R.id.tvUserLoanDur)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
        tvUserId.text = intent.getStringExtra("userID")
        tvUserName.text = intent.getStringExtra("userName")
        tvUserPNo.text = intent.getStringExtra("userPNo")
        tvUserLoanAmt.text = intent.getStringExtra("userLoanAmt")
        tvUserLoanDur.text = intent.getStringExtra("userLoanDur")

        btnDelete.setOnClickListener {
            deleteRecord(tvUserId.text.toString())
        }

        btnUpdate.setOnClickListener{
            openUpdateDialog(intent.getStringExtra("userID").toString(),
                intent.getStringExtra("userName").toString() ) }
    }


    private fun deleteRecord(id: String) {

        val dbRef = FirebaseDatabase.getInstance().getReference("User").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener { Toast.makeText(this, "User data deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, RecordHistory::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener { error -> Toast.makeText(this, "Deleting Error ${error.message}", Toast.LENGTH_LONG).show()
        } }



    private fun openUpdateDialog(userID: String, userName: String)
    {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog, null)

        mDialog.setView(mDialogView)

        val etUserName = mDialogView.findViewById<EditText>(R.id.etUserName)
        val etUserPNo = mDialogView.findViewById<EditText>(R.id.etUserPNo)
        val etUserLoanAmt = mDialogView.findViewById<EditText>(R.id.etUserLoanAmt)
        val etUserLoanDur = mDialogView.findViewById<EditText>(R.id.etUserLoanDur)
        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etUserName.setText(intent.getStringExtra("userName").toString())
        etUserPNo.setText(intent.getStringExtra("userPNo").toString())
        etUserLoanAmt.setText(intent.getStringExtra("userLoanAmt").toString())
        etUserLoanDur.setText(intent.getStringExtra("userLoanDur").toString())

        mDialog.setTitle("Updating Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener { updateUserData(
            userID,
            etUserName.text.toString(),
            etUserPNo.text.toString(),
            etUserLoanAmt.text.toString(),
            etUserLoanDur.text.toString()
        )

            Toast.makeText(applicationContext, "User Data Updated", Toast.LENGTH_LONG).show()

            tvUserName.text = etUserName.text.toString()
            tvUserPNo.text = etUserPNo.text.toString()
            tvUserLoanAmt.text = etUserLoanAmt.text.toString()
            tvUserLoanDur.text = etUserLoanDur.text.toString()
            alertDialog.dismiss()
        } }

    private fun updateUserData(id: String, name: String, pNo: String, loanAmt:String, loanDur:String) {

        val dbRef = FirebaseDatabase.getInstance().getReference("User").child(id)
        val userInfo = Model(id, name, pNo, loanAmt, loanDur)
        dbRef.setValue(userInfo)
        val intent = Intent(this, RecordHistory::class.java)
        finish()
        startActivity(intent)

    }
}