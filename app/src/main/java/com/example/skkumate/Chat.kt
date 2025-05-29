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
    val lastTime: String
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
            val intent = Intent(context, ChatScreen::class.java)
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
        items.add(ChatRoom("Alice Kim", "Are you joining the study session?", R.drawable.profile_img2,  "9:22 PM" ))
        items.add(ChatRoom("Bob Lee", "I'll be there in 30 mins.", R.drawable.profile_img1,  "8:24 PM" ))
        items.add(ChatRoom("Cathy Choi", "Did you take MAP lecture? This week lab session is too hard. Can anybody give me the hint? I will be very happy if you help. If nobody help me, I will be very sad.", R.drawable.profile_img2,  "7:15 PM" ))
        items.add(ChatRoom("Brother", "Hey.", R.drawable.profile_img1,  "4:21 PM" ))
        items.add(ChatRoom("David Park", "See you tomorrow!", R.drawable.profile_img1,  "4:01 PM" ))
        items.add(ChatRoom("Yogiyo", "How was the food?", R.drawable.profile_img1,  "3:24 PM" ))
        items.add(ChatRoom("lorem ipsum", "dolor", R.drawable.profile_img1,  "2:22 PM" ))

        val myAdapter = ChatRoomAdapter(items, this@Chat)
        val listView = findViewById<ListView>(R.id.listViewChatRoom)
        listView.adapter = myAdapter

/*
        val chatList = listOf(
            ChatItem(R.drawable.profile1, "Alice Kim", "Hey! Are you free for lunch?", "10:30 AM"),
            ChatItem(R.drawable.profile2, "Brian Lee", "Let's meet tomorrow.", "9:15 AM"),
            ChatItem(R.drawable.profile3, "Cathy Park", "I'll send you the files.", "Yesterday"),
            ChatItem(R.drawable.profile4, "David Choi", "Good morning!", "2 days ago"),
            ChatItem(R.drawable.profile5, "Ella Jeong", "See you at the event.", "Last week")
        )

        // Set up RecyclerView
        val chatRecyclerView = findViewById<RecyclerView>(R.id.chatRecyclerView)
        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatRecyclerView.adapter = ChatAdapter(chatList)

 */
    }
}