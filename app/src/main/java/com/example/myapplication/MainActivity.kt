package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private val mTag = "MainActivity"
    private lateinit var display: TextView;
    private var isTypingNewNumber = true
    private var currentNumber = "0"
    private var previousNumber = "0"
    private var operator = ""

    // ----- ids ----- //
    private val num_ids = arrayOf(
        R.id.button_num_7, R.id.button_num_8, R.id.button_num_9, R.id.button_num_4,
        R.id.button_num_5, R.id.button_num_6, R.id.button_num_1, R.id.button_num_2,
        R.id.button_num_3, R.id.button_num_0, R.id.button_decimal
    )
    private val op_ids =  arrayOf(
        R.id.button_op_add, R.id.button_op_sub, R.id.button_op_mul, R.id.button_op_div
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(mTag, "onCreate Called")
        setContentView(R.layout.main_layout)
        display = findViewById(R.id.calculator_display);
        setupButtons()
    }

    private fun setupButtons(){
        num_ids.forEach { id ->
            val button: Button = findViewById(id)
            button.setOnClickListener{
                appendNumberToDisplay(button.text.toString())
            }
        }

        op_ids.forEach { buttonId ->
            val button = findViewById<Button>(buttonId)
            button.setOnClickListener { view ->
                setOperator(view.tag.toString())
            }
        }

        // 等号按钮
        findViewById<Button>(R.id.button_equal).setOnClickListener { calculateResult() }

        // 清除按钮
        findViewById<Button>(R.id.button_clear).setOnClickListener { clearDisplay() }

        // 全清除按钮
        findViewById<Button>(R.id.button_all_clear).setOnClickListener { clearAll() }
    }

    private fun setOperator(op: String) {
        if (!isTypingNewNumber) {
            calculateResult()
        }
        operator = op
        previousNumber = currentNumber
        currentNumber = "0"
        isTypingNewNumber = true
    }

    private fun appendNumberToDisplay(number: String){
        if (isTypingNewNumber) {
            display.text = number
            isTypingNewNumber = false
        } else {
            display.text = display.text.toString() + number
        }
    }

    private fun calculateResult() {
        try {
            val result = when (operator) {
                "+" -> previousNumber.toDouble() + currentNumber.toDouble()
                "-" -> previousNumber.toDouble() - currentNumber.toDouble()
                "*" -> previousNumber.toDouble() * currentNumber.toDouble()
                "/" -> previousNumber.toDouble() / currentNumber.toDouble()
                else -> currentNumber.toDouble()
            }
            display.text = result.toString()
            currentNumber = result.toString()
            operator = ""
            isTypingNewNumber = true
        } catch (e: Exception) {
            display.text = "Error"
            isTypingNewNumber = true
            operator = ""
        }
    }

    private fun clearDisplay() {
        display.text = "0"
        isTypingNewNumber = true
    }

    private fun clearAll() {
        display.text = "0"
        currentNumber = "0"
        previousNumber = "0"
        operator = ""
        isTypingNewNumber = true
    }
}