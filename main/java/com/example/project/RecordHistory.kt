package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class RecordHistory : AppCompatActivity() {

    //declare all component
    private lateinit var dbRef: DatabaseReference
    private lateinit var userList: ArrayList<Model>
    private lateinit var btnM: Button
    private lateinit var userRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_history)
        btnM = findViewById<Button>(R.id.btnM)
        Toast.makeText(this, "Read Page", Toast.LENGTH_LONG).show()
        userList = arrayListOf<Model>()
        dbRef = FirebaseDatabase.getInstance().getReference("User")
        userRecyclerView = findViewById<RecyclerView>(R.id.rvUser)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.visibility = View.VISIBLE

        //PUSH TO FIREBASE
        val userID = dbRef.push().key!!

        btnM.setOnClickListener {
            //declare variable i to connect to next pages
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        dbRef.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot){
                if(snapshot.exists())
                {
                    for(userSnap in snapshot.children)
                    {
                        var userData = userSnap.getValue(Model::class.java)
                        userList.add(userData!!)
                        Log.d("oneByone", userData.toString())
                    }

                    Log.d("TrackONE", userList.toString())
                    var mAdapter = UserAdapter(userList)
                    userRecyclerView.adapter = mAdapter
                    userRecyclerView.visibility = View.VISIBLE

                    mAdapter.setOnItemClickListener(object: UserAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@RecordHistory, UserDetailsAct::class.java)

                            //put extras
                            intent.putExtra("userID", userList[position].userID)
                            intent.putExtra("userName", userList[position].userName)
                            intent.putExtra("userPhoneNo", userList[position].userPhoneNo)
                            intent.putExtra("userLoanAmt", userList[position].userLoanAmt)
                            intent.putExtra("userLoanDur", userList[position].userLoanDur)
                            startActivity(intent)

                            Log.d("fbONEX", (userList[position].userID).toString())
                        }
                    })


                }
            }
            override fun onCancelled(error: DatabaseError){
                TODO("Not yet implemented")
            }
        })
    }
}