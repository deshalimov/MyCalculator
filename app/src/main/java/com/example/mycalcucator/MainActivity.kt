package com.example.mycalcucator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    @SuppressLint("SetTextI18n")
    fun onClick(v: View) {
        val rez: TextView = findViewById(R.id.result)
        when (v.id) {
            R.id.button0 -> { rez.text = compilerNumbers(rez.text.toString(), "0") }
            R.id.button1 -> { rez.text = compilerNumbers(rez.text.toString(), "1") }
            R.id.button2 -> { rez.text = compilerNumbers(rez.text.toString(), "2") }
            R.id.button3 -> { rez.text = compilerNumbers(rez.text.toString(), "3") }
            R.id.button4 -> { rez.text = compilerNumbers(rez.text.toString(), "4") }
            R.id.button5 -> { rez.text = compilerNumbers(rez.text.toString(), "5") }
            R.id.button6 -> { rez.text = compilerNumbers(rez.text.toString(), "6") }
            R.id.button7 -> { rez.text = compilerNumbers(rez.text.toString(), "7") }
            R.id.button8 -> { rez.text = compilerNumbers(rez.text.toString(), "8") }
            R.id.button9 -> { rez.text = compilerNumbers(rez.text.toString(), "9") }

            R.id.buttonDelete -> { rez.text = "0" }
            R.id.buttonPoint -> { rez.text = compilerPoints(rez.text.toString(), ".") }
            R.id.buttonRezult -> {
                rez.text = compilerSymbols(rez.text.toString(), "")
                val result = ExpressionBuilder(rez.text.toString()).build().evaluate()
                val isResultLong = result.toLong()
                if(result == isResultLong.toDouble())
                    rez.text = isResultLong.toString()
                else
                    rez.text = result.toString()
            }

            R.id.buttonDivide -> { rez.text = compilerSymbols(rez.text.toString(), "/") }
            R.id.buttonMultiply -> { rez.text = compilerSymbols(rez.text.toString(), "*") }
            R.id.buttonPlus -> { rez.text = compilerSymbols(rez.text.toString(), "+") }
            R.id.buttonMinus -> { rez.text = compilerSymbols(rez.text.toString(), "-") }
        }
    }

    // только для чисел
    private fun compilerNumbers(text: String, symbol: String): String{
        return if (text == "0" && symbol == "0")
            text
        else if(text == "0" && symbol != "0" && Regex("[*+-/.]").find(symbol) == null)
            symbol
        else
            text + symbol
    }

    // для удаления повторного ввода символов и для равно
    // повторные символы будут заменяться на последний выбранный
    private fun compilerSymbols(text: String, come: String): String{
        return if(Regex("[*+-/.]\$").find(text) != null)
            text.substring(0, text.length - 1) + come
        else
            text + come
    }

    // Если точка уже стоит в выражении,
    // или перед точкой стоят символы арифметики,
    // то возвращается исходный текст
    private fun compilerPoints(text: String, come: String): String{
        return if(Regex("\\.\\d*\$").find(text) != null
            || Regex("[*+-/]\$").find(text) != null)
            text
        else
            text + come
    }
}