package com.example.testinput

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.testinput.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val myName: MyName = MyName("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(binding.root)

        binding.myName = myName

        binding.btnOK.setOnClickListener {
            addNickname(it)
        }

        binding.btnReset.setOnClickListener {
            binding.apply {
                myName?.nickname = ""
                invalidateAll()
                inputName.visibility = View.VISIBLE
                btnOK.visibility = View.VISIBLE
                tvName.visibility = View.GONE
            }
        }
    }

    private fun clickHandlerFunction(viewThatIsClicked: View) {
        // Add code to perform the button click event
    }

    private fun addNickname(view: View) {
        val editText = findViewById<EditText>(R.id.inputName)
        val nicknameTextView = findViewById<TextView>(R.id.tvName)

        binding.apply {
            myName?.nickname = inputName.text.toString()
            invalidateAll()
            inputName.visibility = View.GONE
            btnOK.visibility = View.VISIBLE
            tvName.visibility = View.VISIBLE
        }

        // Hide the keyboard.
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun updateNickname (view: View) {
        val editText = findViewById<EditText>(R.id.inputName)
        val doneButton = findViewById<Button>(R.id.btnOK)

        editText.visibility = View.VISIBLE
        doneButton.visibility = View.VISIBLE
        view.visibility = View.GONE

        // Set the focus to the edit text.
        editText.requestFocus()

        // Show the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, 0)
    }
}