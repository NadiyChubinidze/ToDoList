package com.example.todolist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class MyAdapter(private val list:LinkedList<Item>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val caseText: TextView = itemView.findViewById(R.id._case)
        val dateText: TextView = itemView.findViewById(R.id.date)
        private val checkDone: CheckBox = itemView.findViewById(R.id.checkDone)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.deleteItem)
        private var mAdapter: MyAdapter? = null

        constructor(itemView: View, adapter: MyAdapter) : this(itemView) {
            mAdapter = adapter
            deleteButton.setOnClickListener(View.OnClickListener {
                val mPosition = layoutPosition
                adapter.list.removeAt(mPosition)
                adapter.notifyItemRemoved(mPosition)
            })
            checkDone.setOnClickListener(View.OnClickListener {
                if (checkDone.isChecked) {
                    val mPosition = layoutPosition
                    adapter.list[mPosition].done = true
                }
                else {
                    val mPosition = layoutPosition
                    adapter.list[mPosition].done = false
                }
            })
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.custom_item,
            parent,false)
        return MyViewHolder(itemView, this)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = list[position]
        holder.caseText.text = currentItem.case
        holder.dateText.text = currentItem.date
    }

    override fun getItemCount(): Int {
        return list.size
    }
}