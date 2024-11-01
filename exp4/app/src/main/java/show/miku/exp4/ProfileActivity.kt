package show.miku.exp4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    private lateinit var logoutButton: Button
    private lateinit var usernameText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // MainActivity
        val mainIntent = Intent(this, MainActivity::class.java)

        // Set username
        usernameText = findViewById(R.id.text_username)
        val dist = "欢迎您，" + intent?.extras?.getString("username").toString()
        usernameText.text = dist

        // Setup logout button
        logoutButton = findViewById(R.id.button_logout)
        logoutButton.setOnClickListener{
            startActivity(mainIntent)
        }
    }
}