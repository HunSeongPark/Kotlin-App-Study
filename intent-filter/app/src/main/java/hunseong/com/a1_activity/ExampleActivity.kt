package hunseong.com.a1_activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ExampleActivity : AppCompatActivity() {

    val txt: String = "Hi"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
        val button = findViewById<Button>(R.id.button)

        // Text Send를 위한 Intent
        button.setOnClickListener {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, txt)
            }
            startActivity(sendIntent)
        }
    }


}