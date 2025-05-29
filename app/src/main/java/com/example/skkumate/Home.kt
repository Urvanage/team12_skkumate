package com.example.skkumate

import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.card.MaterialCardView

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val dormButton = findViewById<MaterialCardView>(R.id.dormButton)
        val interestButton = findViewById<MaterialCardView>(R.id.interestButton)

        dormButton.setOnClickListener {

        }

        interestButton.setOnClickListener {

        }

        // 기본으로 Home 선택
        bottomNavigationView.selectedItemId = R.id.navigation_home

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_profile -> {
                    val intent = Intent(this, Profile::class.java)
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.navigation_home -> {
                    // 기본 Home으로 이동할 때는 아무 작업도 필요 없음
                    true
                }
                R.id.navigation_chat -> {
                    // Chat 화면으로 이동
                    val intent = Intent(this, Chat::class.java)
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                    true
                }
                else -> false
            }
        }
    }
}