package com.example.exp3

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {
    // EditText
    private lateinit var classEditText: EditText
    private lateinit var idEditText: EditText
    private lateinit var nameEditText: EditText
    // Button
    private lateinit var submitButton: Button
    // ListView
    private lateinit var listView: ListView
    // Data
    private val students = ArrayList<Map<String, Any>>()
    // Adapter
    private lateinit var adapter: SimpleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.layout_main)

        classEditText = findViewById(R.id.editClass)
        idEditText = findViewById(R.id.editId)
        nameEditText = findViewById(R.id.editName)
        submitButton = findViewById(R.id.btnSubmit)
        listView = findViewById(R.id.listView)

        // Init Data
        val testStudent = HashMap<String, Any>()
        testStudent["name"] = "姓名"
        testStudent["id"] = "学号"
        testStudent["class"] = "班级"
        students.add(testStudent)

        // Init adapter
        adapter = SimpleAdapter(this, students, R.layout.layout_list, arrayOf("class", "id", "name"), intArrayOf(R.id.textViewItemClass, R.id.textViewItemId, R.id.textViewName))
        listView.adapter = adapter

        // Set Btn Onclick Listener
        submitButton.setOnClickListener{onSubmit()}
    }

    private fun onSubmit() {
        if (nameEditText.text.isNullOrEmpty() || idEditText.text.isNullOrEmpty() || classEditText.text.isNullOrEmpty()) {
            Toast.makeText(this, "所有字段都是必填项，请填写完整", Toast.LENGTH_SHORT).show()
        } else {
            val newStudent = HashMap<String, Any>()
            newStudent["name"] = nameEditText.text.toString()
            newStudent["id"] = idEditText.text.toString()
            newStudent["class"] = classEditText.text.toString()
            students.add(newStudent)
            adapter.notifyDataSetChanged()
            nameEditText.setText("")
            idEditText.setText("")
            classEditText.setText("")
            Toast.makeText(this, "学生信息已添加", Toast.LENGTH_SHORT).show()
        }
    }
}