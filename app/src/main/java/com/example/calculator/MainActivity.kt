package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var stmError = false
    private var lastDot = false
    private var lastNum = false

    private lateinit var expression: Expression

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun clearAll(view: View) {
        binding.textOperation.text = ""
        binding.result.text = "0"
        stmError = false
        lastDot = false
        lastNum = false
    }

    fun backSpace(view: View) {
        val currentText = binding.textOperation.text.toString()
        if (currentText.isNotEmpty()) {
            binding.textOperation.text = if (currentText.length == 1) "" else currentText.substring(0, currentText.length - 1)
            if (binding.textOperation.text.isEmpty()) {
                binding.result.text = "0"
            }
        }
    }
    fun operator(view: View) {
        if (lastNum && !stmError) {
            binding.textOperation.append((view as Button).text)
            lastDot = false
            lastNum = false
        }
    }

    fun num(view: View) {
        if (stmError) {
            binding.textOperation.text = (view as Button).text
            stmError = false
        } else {
            if (binding.result.text == "0") {
                binding.textOperation.append((view as Button).text)
            } else {
                binding.textOperation.append((view as Button).text)
            }
        }
        lastNum = true
    }

    fun dot(view: View) {
        if (lastNum && !lastDot && !stmError) {
            binding.textOperation.append(".")
            lastDot = true
            lastNum = false
        }
    }

    fun equal(view: View) {
        val expression = binding.textOperation.text.toString().replace("×", "*").replace("÷", "/")
        try {
            val exp = ExpressionBuilder(expression).build()
            val result = exp.evaluate()
            if (result == result.toInt().toDouble()) {
                binding.result.visibility = View.VISIBLE
                binding.result.text = result.toInt().toString()
            } else {
                binding.result.visibility = View.VISIBLE
                binding.result.text = result.toString()
            }
        } catch (e: Exception) {
            stmError = true
            binding.result.text = "Error"
            binding.textOperation.text = ""
        }
    }
}
