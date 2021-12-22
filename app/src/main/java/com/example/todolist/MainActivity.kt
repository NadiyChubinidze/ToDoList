package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val list = LinkedList<Item>()
    private val adapter = MyAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_list)

        Log.d("SIZE LIST", list.size.toString())
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val addButton = findViewById<FloatingActionButton>(R.id.add_button)
        addButton.setOnClickListener(View.OnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            val textEditText = EditText(this)
            alertDialog.setTitle("Добавить новое дело")
            alertDialog.setMessage("Введите дело")
            alertDialog.setView(textEditText)
            alertDialog.setPositiveButton("Добавить") { dialog, i->
                val currentDate = Date()
                val dateFormat: DateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                val dateText: String = dateFormat.format(currentDate)
                val item = Item(textEditText.text.toString(), dateText, false)
                list.add(item)
                adapter.notifyItemInserted(list.size-1)
                recyclerView.smoothScrollToPosition(list.size-1)
                dialog.dismiss()
            }
            alertDialog.setNegativeButton("Отмена"){dialog, i->
                dialog.dismiss()
            }

            alertDialog.show()
        })
    }
}