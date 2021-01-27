package com.example.calculator

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private var one: Button? = null
    private var two: Button? = null
    private var three: Button? = null
    private var four: Button? = null
    private var five: Button? = null
    private var six: Button? = null
    private var seven: Button? = null
    private var eight: Button? = null
    private var nine: Button? = null
    private var zero: Button? = null
    private var plus: Button? = null
    private var minus: Button? = null
    private var times: Button? = null
    private var divide: Button? = null
    private var equal: Button? = null
    private var point: Button? = null
    private var restart: Button? = null
    private var back: Button? = null
    private var power: Button? = null
    private var result: String? = null
    private var input: String? = ""
    private var clearResult = false
    var problem: TextView? = null
    var resultView: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        one = findViewById(R.id.one)
        two = findViewById(R.id.two)
        three = findViewById(R.id.three)
        four = findViewById(R.id.four)
        five = findViewById(R.id.five)
        six = findViewById(R.id.six)
        seven = findViewById(R.id.seven)
        eight = findViewById(R.id.eight)
        nine = findViewById(R.id.nine)
        zero = findViewById(R.id.zero)
        plus = findViewById(R.id.plus)
        minus = findViewById(R.id.minus)
        divide = findViewById(R.id.divide)
        times = findViewById(R.id.times)
        back = findViewById(R.id.back)
        power = findViewById(R.id.power)
        restart = findViewById(R.id.C)
        point = findViewById(R.id.point)
        equal = findViewById(R.id.equal)
        problem = findViewById(R.id.problem)
        resultView = findViewById(R.id.resultView)
    }

    fun item(view: View) {
        val button = view as Button

        when (val data = button.text.toString()) {
            "C" -> {
                input = ""
                result = "0"
            }
            "x" -> {
                clearResult = false
                Solve()
                input += "*"
            }
            "^" -> {
                clearResult = false
                Solve()
                input += "^"
            }
            "=" -> {
                clearResult = true
                Solve()
                result = input
            }
            "←" -> if (input!!.isNotEmpty()) {
                clearResult = false
                val newText = input!!.substring(0, input!!.length - 1)
                input = newText
            }
            else -> {
                if (input == null) {
                    input = ""
                }
                if (data == "+" || data == "-" || data == "/") {
                    clearResult = false
                    Solve()
                } else if (clearResult) {
                    input = ""
                    clearResult = false
                }
                input += data
            }
        }
        problem!!.text = input
        resultView!!.text = result
    }

    private fun Solve() {
        if (input!!.split("×").toTypedArray().size == 2) {
            val number = input!!.split("×").toTypedArray()
            try {
                val mul = number[0].toDouble() * number[1].toDouble()
                input = mul.toString() + ""
            } catch (e: Exception) {
            }
        } else if (input!!.split("-").toTypedArray().size == 2) {
            val number = input!!.split("-").toTypedArray()
            if (number[0] === "" && number.size == 2) {
                number[0] = 0.toString() + ""
            }
            try {
                var sub = 0.0
                if (number.size == 2) {
                    sub = number[0].toDouble() - number[1].toDouble()
                } else if (number.size == 3) {
                    sub = -number[1].toDouble() - number[2].toDouble()
                }
                input = sub.toString() + ""
            } catch (e: Exception) {
            }
            val n = input!!.split("\\.").toTypedArray()
            if (n.size > 1) {
                if (n[1] == "0") {
                    input = n[0]
                }
            }
        } else if (input!!.split("\\+").toTypedArray().size == 2) {
            val number = input!!.split("\\+").toTypedArray()
            try {
                val sum = number[0].toDouble() + number[1].toDouble()
                input = sum.toString() + ""
            } catch (e: Exception) {
            }
        } else if (input!!.split("÷").toTypedArray().size == 2) {
            val number = input!!.split("÷").toTypedArray()
            try {
                val div = number[0].toDouble() / number[1].toDouble()
                input = div.toString() + ""
            } catch (e: Exception) {
            }
        } else if (input!!.split("\\^").toTypedArray().size == 2) {
            val number = input!!.split("\\^").toTypedArray()
            try {
                val pow = number[0].toDouble().pow(number[1].toDouble())
                input = pow.toString() + ""
            } catch (e: Exception) {
            }
            problem!!.text = input
        }
    }
}




