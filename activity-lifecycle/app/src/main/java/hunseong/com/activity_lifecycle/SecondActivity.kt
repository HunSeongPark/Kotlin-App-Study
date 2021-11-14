package hunseong.com.activity_lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        Log.d("Lifecycle", "SecondActivity : called onCreate()")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", "SecondActivity : called onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", "SecondActivity : called onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", "SecondActivity : called onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", "SecondActivity : called onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Lifecycle", "SecondActivity : called onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", "SecondActivity : called onDestroy()")
    }
}