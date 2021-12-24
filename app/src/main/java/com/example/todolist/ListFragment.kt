package com.example.todolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class ListFragment : Fragment() {

    private val list = LinkedList<Item>()
    private val adapter = MyAdapter(list)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.setHasFixedSize(true)

        val addButton = view.findViewById<FloatingActionButton>(R.id.add_button)
        addButton.setOnClickListener(View.OnClickListener {
            val alertDialog = AlertDialog.Builder(view.context)
            val textEditText = EditText(view.context)
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
        // Inflate the layout for this fragment
        return view
    }


}