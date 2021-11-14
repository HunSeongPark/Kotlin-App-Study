package hunseong.com.activity_lifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("Lifecycle", "MainActivity : called onCreate()")

        val btn = findViewById<Button>(R.id.btn)

        btn.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "MainActivity : called onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "MainActivity : called onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "MainActivity : called onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "MainActivity : called onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Lifecycle", "MainActivity : called onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "MainActivity : called onDestroy()")
    }
}