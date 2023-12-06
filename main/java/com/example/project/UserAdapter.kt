package com.example.project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

//create adapter call data into recycle
class UserAdapter (private val userList: ArrayList<Model>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>()   {

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_list_item,parent,false)
        return ViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.tvUserName.text = currentUser.userName
        holder.tvUserPNo.text = currentUser.userPhoneNo
        holder.tvUserLoanAmt.text = currentUser.userLoanAmt
        holder.tvUserLoanDur.text = currentUser.userLoanDur
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder (itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val tvUserName : TextView = itemView.findViewById(R.id.tvUserName)
        val tvUserPNo : TextView = itemView.findViewById(R.id.tvUserPNo)
        val tvUserLoanAmt : TextView = itemView.findViewById(R.id.tvUserLoanAmt)
        val tvUserLoanDur : TextView = itemView.findViewById(R.id.tvUserLoanDur)

        init {
            itemView.setOnClickListener {clickListener.onItemClick(adapterPosition)
            }
        }
    }
}