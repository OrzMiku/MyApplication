package show.miku.myapplication

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.myapplication.R

class MainActivity : ComponentActivity() {
    private val debugTag = "Debug"
    private lateinit var display: TextView
    private var isTypingNewNumber = true
    private var currentNumber = "0"
    private var previousNumber = "0"
    private var operator = ""

    // ----- ids ----- //
    private val numIds = arrayOf(
        R.id.button_num_7, R.id.button_num_8, R.id.button_num_9, R.id.button_num_4,
        R.id.button_num_5, R.id.button_num_6, R.id.button_num_1, R.id.button_num_2,
        R.id.button_num_3, R.id.button_num_0, R.id.button_num_decimal
    )
    private val opIds =  arrayOf(
        R.id.button_op_add, R.id.button_op_sub, R.id.button_op_mul, R.id.button_op_div
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        display = findViewById(R.id.calculator_display)
        setupButtons()
    }

    private fun setupButtons(){
        numIds.forEach { id ->
            val button: Button = findViewById(id)
            button.setOnClickListener{
                appendNumberToDisplay(button.text.toString())
            }
        }

        opIds.forEach { buttonId ->
            val button = findViewById<Button>(buttonId)
            button.setOnClickListener { view ->
                setOperator(view.tag.toString())
            }
        }

        // 等号按钮
        findViewById<Button>(R.id.button_op_equal).setOnClickListener { calculateResult() }

        // 清除按钮
        findViewById<Button>(R.id.button_op_clear).setOnClickListener { clearDisplay() }

        // 全清除按钮
        findViewById<Button>(R.id.button_op_all_clear).setOnClickListener { clearAll() }
    }

    private fun setOperator(op: String) {
        Log.d(debugTag, isTypingNewNumber.toString())
        if (!isTypingNewNumber) {
            calculateResult()
        }
        operator = op
        previousNumber = currentNumber
        currentNumber = "0"
        isTypingNewNumber = true
    }

    @SuppressLint("SetTextI18n")
    private fun appendNumberToDisplay(number: String){
        if (isTypingNewNumber) {
            display.text = number
            currentNumber = number
            isTypingNewNumber = false
        } else {
            display.text = display.text.toString() + number
            currentNumber += number
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
            display.text = getString(R.string.error_msg)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onMenuItemSelected(featureId: Int, item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_calculator -> {
                val intent: Intent = Intent(this, CalculatorActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }
}