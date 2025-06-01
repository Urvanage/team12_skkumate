package com.example.skkumate

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class ChatRoom(
    val name: String,
    val lastChat: String,
    val thumbnail: Int,
    val lastTime: String,
    val targetActivity: Class<out AppCompatActivity>
) {

}

class ChatRoomAdapter(val data: ArrayList<ChatRoom>, val context: Context): BaseAdapter() {
    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(p0: Int): Any {
        return data[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val generatedView = inflater.inflate(R.layout.chatlist, null)

        val textViewName = generatedView.findViewById<TextView>(R.id.textViewRoomName)
        val textViewChat = generatedView.findViewById<TextView>(R.id.textViewRecentChat)
        val textViewTime = generatedView.findViewById<TextView>(R.id.textViewLastTime)
        val imageViewThumbnail = generatedView.findViewById<ImageView>(R.id.imageViewProfile)

        textViewName.text = data[p0].name
        textViewChat.text = data[p0].lastChat
        textViewTime.text = data[p0].lastTime
        imageViewThumbnail.setImageResource(data[p0].thumbnail)

        generatedView.setOnClickListener {
            val intent = Intent(context, data[p0].targetActivity)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)  // 플래그 추가
            context.startActivity(intent)
        }

        return generatedView
    }
}



class Chat : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // 기본으로 Home 선택
        bottomNavigationView.selectedItemId = R.id.navigation_chat

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_profile -> {
                    val intent = Intent(this, Profile::class.java)
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.navigation_home -> {
                    val intent = Intent(this, Home::class.java)
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.navigation_chat -> {
                    // Chat 화면으로 이동
                    //val intent = Intent(this, Chat::class.java)
                    //startActivity(intent)
                    //overridePendingTransition(0, 0)
                    true
                }
                else -> false
            }
        }

        val items = ArrayList<ChatRoom>()
        items.add(ChatRoom("Alice Kim", "test 1", R.drawable.profile_img2,  "9:22 PM" , ChatScreen::class.java))
        items.add(ChatRoom("Bob Lee", "test 2", R.drawable.profile_img1,  "8:24 PM", ChatScreen2::class.java))
        items.add(ChatRoom("Cathy Choi", "test 3", R.drawable.profile_img2,  "7:15 PM", ChatScreen3::class.java ))

        val myAdapter = ChatRoomAdapter(items, this@Chat)
        val listView = findViewById<ListView>(R.id.listViewChatRoom)
        listView.adapter = myAdapter

    }
}