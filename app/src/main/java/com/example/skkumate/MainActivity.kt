package com.example.skkumate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.content.Intent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signup = findViewById<Button>(R.id.button3)
        val login = findViewById<Button>(R.id.button)
        val findIdPass = findViewById<Button>(R.id.button2)

        signup.setOnClickListener {
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
            finish()
        }

        login.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
            finish()
        }

        findIdPass.setOnClickListener {
            val intent = Intent(this, FindIdPass::class.java)
            startActivity(intent)
            finish()
        }

        /*
        val signup = findViewById<Button>(R.id.textView3)
        val submit = findViewById<Button>(R.id.button)
        val id = findViewById<EditText>(R.id.editTextText)
        val pass = findViewById<EditText>(R.id.editTextPassword)

        submit.setOnClickListener {
            val idtxt = id.text.toString()
            val password = pass.text.toString()

            //val intent = Intent(this, HomeActivity::class.java)
            //startActivity(intent)
            //finish()

            // 로그인 실패 시 알림
            /*
            Toast.makeText(this, "Invalid ID or password", Toast.LENGTH_SHORT).show()
            id.text.clear()
            pass.text.clear()
            */
        }


        /*
        */

         */
    }
}