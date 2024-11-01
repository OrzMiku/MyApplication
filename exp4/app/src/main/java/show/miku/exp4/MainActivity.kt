package show.miku.exp4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var usernameEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ProfileActivity
        val profileIntent = Intent(this, ProfileActivity::class.java)

        // Get username EditTextView
        usernameEditText = findViewById(R.id.edit_text_username)

        // Setup login button
        loginButton = findViewById(R.id.button_login)
        loginButton.setOnClickListener{
                val username = usernameEditText.text.toString()
                if(username == "")
                    Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show()
                else{
                    profileIntent.putExtra("username", username)
                    startActivity(profileIntent)
                }
        }
    }
}
