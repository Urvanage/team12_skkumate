package com.example.skkumate

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


object MessageStore2 {
    val messages = ArrayList<Message>()
}

class ChatScreen2 : AppCompatActivity(){
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messageList: ArrayList<Message>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_screen)

        val messageEditText = findViewById<EditText>(R.id.messageEditText)
        val sendButton = findViewById<ImageView>(R.id.sendButton)
        val messageListView = findViewById<RecyclerView>(R.id.messageListView)

        messageList = MessageStore2.messages
        messageAdapter = MessageAdapter(this, messageList)

        messageListView.layoutManager = LinearLayoutManager(this)
        messageListView.adapter = messageAdapter

        if (messageList.isEmpty()) {
            Handler(Looper.getMainLooper()).postDelayed({
                val message = Message("test 2", "Now", false)
                messageList.add(message)
                messageAdapter.notifyItemInserted(messageList.size - 1)
                messageListView.scrollToPosition(messageList.size - 1)
            }, 1000)
        }

        sendButton.setOnClickListener {
            val messageText = messageEditText.text.toString().trim()
            if (messageText.isNotEmpty()) {
                val message = Message(messageText, "Now", true)
                messageList.add(message)
                messageAdapter.notifyItemInserted(messageList.size - 1)
                messageEditText.text.clear()
                messageListView.scrollToPosition(messageList.size - 1)

                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as android.view.inputmethod.InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(messageEditText.windowToken, 0)
            }
        }
    }
}
