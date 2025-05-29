package com.example.skkumate

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class Message(
    val text: String,
    val time: String,
    val isSentByUser: Boolean
)

class MessageAdapter(
    private val context: Context,
    private val messageList: ArrayList<Message>
) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageText: TextView = itemView.findViewById(R.id.messageText)
        val messageTime: TextView = itemView.findViewById(R.id.messageTime)
        val messageContainer: View = itemView.findViewById(R.id.messageContainer)
        val profileImage: ImageView = itemView.findViewById(R.id.profileImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.message_item, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messageList[position]
        holder.messageText.text = message.text
        holder.messageTime.text = message.time

        // Align messages based on sender
        if (message.isSentByUser) {
            holder.messageContainer.setBackgroundResource(R.drawable.message_bubble)
            holder.profileImage.visibility = View.GONE
        } else {
            holder.messageContainer.setBackgroundResource(R.drawable.message_bubble)
            holder.profileImage.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }
}

class ChatScreen : AppCompatActivity() {
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messageList: ArrayList<Message>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_screen)

        val messageEditText = findViewById<EditText>(R.id.messageEditText)
        val sendButton = findViewById<ImageView>(R.id.sendButton)
        val messageListView = findViewById<RecyclerView>(R.id.messageListView)

        // Initialize message list
        messageList = ArrayList()
        messageAdapter = MessageAdapter(this, messageList)

        messageListView.layoutManager = LinearLayoutManager(this)
        messageListView.adapter = messageAdapter

        // Send message
        sendButton.setOnClickListener {
            val messageText = messageEditText.text.toString().trim()
            if (messageText.isNotEmpty()) {
                val message = Message(messageText, "Now", true)
                messageList.add(message)
                messageAdapter.notifyItemInserted(messageList.size - 1)
                messageEditText.text.clear()
                messageListView.scrollToPosition(messageList.size - 1)
            }
        }
    }
}

