package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var lastNumeric: Boolean = false
    private var lastDot: Boolean = false
    private var stateError: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    fun num(view: View) {
        if (stateError) {
            binding.textOperation.text = (view as Button).text
            stateError = false
        } else {
            binding.textOperation.append((view as Button).text)
        }
        lastNumeric = true
    }

    fun operator(view: View) {
        if (!stateError) {
            val inputText = binding.textOperation.text.toString()
            val lastChar = inputText.lastOrNull()

            if (lastChar != null && !lastChar.isDigit() && lastChar.toString() != ")" && lastChar.toString() != "(") {
                binding.textOperation.text = inputText.dropLast(1)
            }

            binding.textOperation.append((view as Button).text)
            lastDot = false
            lastNumeric = false
        }
    }

    fun clearAll(view: View) {
        binding.textOperation.text = ""
        binding.result.text = "0"
        lastNumeric = false
        lastDot = false
        stateError = false
    }

    fun backSpace(view: View) {
        if (binding.textOperation.text.isNotEmpty()) {
            binding.textOperation.text = binding.textOperation.text.toString().dropLast(1)
        }
    }

    fun dot(view: View) {
        if (lastNumeric && !lastDot) {
            binding.textOperation.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun equal(view: View) {
        val expressionText = binding.textOperation.text.toString().replace("÷", "/").replace("×", "*")
        try {
            val expression = ExpressionBuilder(expressionText).build()
            val result = expression.evaluate()
            val longResult = result.toLong()
            if (result == longResult.toDouble()) {
                binding.result.text = longResult.toString()
            } else {
                binding.result.text = result.toString()
            }
        } catch (e: Exception) {
            binding.result.text = "Error"
            stateError = true
            e.printStackTrace()
        }
    }
}
