package com.example.calculator

<<<<<<< HEAD
import android.annotation.SuppressLint
=======
>>>>>>> 506382d04e61f7827641780b109c07f6e3f80d06
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
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
<<<<<<< HEAD

=======
    private lateinit var vibrator: Vibrator
>>>>>>> 506382d04e61f7827641780b109c07f6e3f80d06

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the vibrator service
        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

<<<<<<< HEAD

    fun num(view: View) {
=======
    private fun vibratePhone(duration: Long = 100) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(duration)
        }
    }

    fun num(view: View) {
        vibratePhone() // Call vibration
>>>>>>> 506382d04e61f7827641780b109c07f6e3f80d06
        if (stateError) {
            binding.textOperation.text = (view as Button).text
            stateError = false
        } else {
            binding.textOperation.append((view as Button).text)
        }
        lastNumeric = true
<<<<<<< HEAD
        vibratePhone(50)
    }

    fun operator(view: View) {
=======
    }

    fun operator(view: View) {
        vibratePhone() // Call vibration
>>>>>>> 506382d04e61f7827641780b109c07f6e3f80d06
        if (!stateError) {
            val inputText = binding.textOperation.text.toString()
            val lastChar = inputText.lastOrNull()

            if (lastChar != null && !lastChar.isDigit() && lastChar.toString() != ")" && lastChar.toString() != "(") {
                binding.textOperation.text = inputText.dropLast(1)
            }

            binding.textOperation.append((view as Button).text)
            lastDot = false
            lastNumeric = false
<<<<<<< HEAD
            vibratePhone(50)
=======
>>>>>>> 506382d04e61f7827641780b109c07f6e3f80d06
        }
    }

    fun clearAll(view: View) {
<<<<<<< HEAD
=======
        vibratePhone() // Call vibration
>>>>>>> 506382d04e61f7827641780b109c07f6e3f80d06
        binding.textOperation.text = ""
        binding.result.text = "0"
        lastNumeric = false
        lastDot = false
        stateError = false
<<<<<<< HEAD
        vibratePhone(55)
    }

    fun backSpace(view: View) {
        if (binding.textOperation.text.isNotEmpty()) {
            binding.textOperation.text = binding.textOperation.text.toString().dropLast(1)
        }
        vibratePhone(55)
    }

    fun dot(view: View) {
=======
    }

    fun backSpace(view: View) {
        vibratePhone() // Call vibration
        if (binding.textOperation.text.isNotEmpty()) {
            binding.textOperation.text = binding.textOperation.text.toString().dropLast(1)
        }
    }

    fun dot(view: View) {
        vibratePhone() // Call vibration
>>>>>>> 506382d04e61f7827641780b109c07f6e3f80d06
        if (lastNumeric && !lastDot) {
            binding.textOperation.append(".")
            lastNumeric = false
            lastDot = true
        }
        vibratePhone(50)
    }

    fun equal(view: View) {
<<<<<<< HEAD
=======
        vibratePhone() // Call vibration
>>>>>>> 506382d04e61f7827641780b109c07f6e3f80d06
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
<<<<<<< HEAD
        }
        vibratePhone(55)
    }


    private fun Context.vibratePhone(duration: Long = 100) {
        val vib = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vib.vibrate(VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vib.vibrate(duration)
=======
>>>>>>> 506382d04e61f7827641780b109c07f6e3f80d06
        }
    }
}
