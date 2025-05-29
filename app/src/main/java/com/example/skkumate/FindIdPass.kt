package com.example.skkumate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class FindIdPass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_id_pass)

        val tabFindID = findViewById<TextView>(R.id.tabFindID)
        val tabFindPassword = findViewById<TextView>(R.id.tabFindPassword)
        val underline = findViewById<View>(R.id.underline)

        val idContainer = findViewById<LinearLayout>(R.id.idContainer)
        val passwordContainer = findViewById<LinearLayout>(R.id.passwordContainer)

        val bakcbtn = findViewById<ImageView>(R.id.backButton)

        bakcbtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        tabFindID.setTextColor(resources.getColor(R.color.primary))
        tabFindPassword.setTextColor(resources.getColor(R.color.gray))
        underline.layoutParams.width = tabFindID.width
        underline.x = tabFindID.x
        underline.requestLayout()

        // ID 찾기 탭
        tabFindID.setOnClickListener {
            tabFindID.setTextColor(resources.getColor(R.color.primary))
            tabFindPassword.setTextColor(resources.getColor(R.color.gray))

            underline.animate().x(tabFindID.x).setDuration(200).start()
            underline.layoutParams.width = tabFindID.width
            underline.requestLayout()

            idContainer.visibility = View.VISIBLE
            passwordContainer.visibility = View.GONE
        }

        // 비밀번호 찾기 탭
        tabFindPassword.setOnClickListener {
            tabFindPassword.setTextColor(resources.getColor(R.color.primary))
            tabFindID.setTextColor(resources.getColor(R.color.gray))

            underline.animate().x(tabFindPassword.x).setDuration(200).start()
            underline.layoutParams.width = tabFindPassword.width
            underline.requestLayout()

            idContainer.visibility = View.GONE
            passwordContainer.visibility = View.VISIBLE
        }
    }
}