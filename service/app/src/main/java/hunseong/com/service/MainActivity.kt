package hunseong.com.service

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import hunseong.com.service.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val binderExtendedServiceConnection = object : ServiceConnection {

        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            binderExtendedServiceBinder = p1 as MyBinderExtendedService.MyBinder
            Log.e("binder", "onServiceConnected: ")

        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            binderExtendedServiceBinder = null
            Log.e("binder", "onServiceDisconnected: ")

        }

    }

    private var binderExtendedServiceBinder: MyBinderExtendedService.MyBinder? = null

    private val messengerIPCHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            Log.e("handleLog", "handleMessage: activity", )
            when (msg.what) {
                MyMessengerIPCService.MSG_ADD_RESPONSE -> Toast.makeText(this@MainActivity,
                    "Add response : ${msg.arg1}",
                    Toast.LENGTH_SHORT)
                    .show()
                else -> super.handleMessage(msg)
            }
        }
    }

    private val messengerIPCClient = Messenger(messengerIPCHandler)
    private var messengerIPCService: Messenger? = null
    private val messengerIPCServiceConnection = object : ServiceConnection {

        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            messengerIPCService = Messenger(p1).apply {
                send(Message.obtain(null, MyMessengerIPCService.MSG_BIND_CLIENT, 0, 0).apply {
                    replyTo = messengerIPCClient
                })
            }
            Toast.makeText(this@MainActivity,
                "MessengerIPCService - onServiceConnected",
                Toast.LENGTH_SHORT).show()
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            messengerIPCService = null
            Toast.makeText(this@MainActivity,
                "MessengerIPCService - onServiceDisconnected",
                Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setButtons()
    }

    private fun setButtons() = with(binding) {

        // Basic Service
        startService.setOnClickListener {
            startBasicService()
        }

        stopService.setOnClickListener {
            stopBasicService()
        }


        // Foreground Service
        startForegroundService.setOnClickListener {
            startForeground()
        }

        stopForegroundService.setOnClickListener {
            stopForeground()
        }


        // binder extended service
        bindBinderService.setOnClickListener {
            bindBinderExtendedService()
        }

        unbindBinderService.setOnClickListener {
            unbindBinderExtendedService()
        }

        showToastBinder.setOnClickListener {
            showBinderExtendedServiceToast()
        }


        // IPC service
        bindIpcService.setOnClickListener {
            bindMessengerService()
        }

        unbindIpcService.setOnClickListener {
            unbindMessengerService()
        }

        showToastIpc.setOnClickListener {
            showMessengerIPCServiceToast()
        }

        addFunctionIpc.setOnClickListener {
            invokeAddMessengerIPCService()
        }

    }

    private fun startBasicService() {
        Intent(this, MyService::class.java).run {
            startService(this)
        }
    }

    private fun stopBasicService() {
        Intent(this, MyService::class.java).run {
            stopService(this)
        }
    }

    private fun startForeground() {
        Intent(this, MyForegroundService::class.java).run {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) startForegroundService(this)
            else startService(this)
        }
    }

    private fun stopForeground() {
        Intent(this, MyForegroundService::class.java).run {
            stopService(this)
        }
    }

    private fun bindBinderExtendedService() {

        Intent(this, MyBinderExtendedService::class.java).run {
            bindService(this, binderExtendedServiceConnection, Service.BIND_AUTO_CREATE)
            Log.e("binder", "bindBinderExtendedService: ")
        }
    }

    private fun unbindBinderExtendedService() {
        unbindService(binderExtendedServiceConnection)
        Log.e("binder", "unbind: ")

    }

    private fun showBinderExtendedServiceToast() {
        binderExtendedServiceBinder?.run {
            service.showToast("Binder extended Service")
            Log.e("binder", "showtoast: ")
        }
    }

    private fun bindMessengerService() {
        Intent(this, MyMessengerIPCService::class.java).run {
            bindService(this, messengerIPCServiceConnection, Service.BIND_AUTO_CREATE)
        }
    }

    private fun unbindMessengerService() {
        messengerIPCService?.send(Message.obtain(null,
            MyMessengerIPCService.MSG_UNBIND_CLIENT,
            0,
            0).apply {
            replyTo = messengerIPCClient
        })
        unbindService(messengerIPCServiceConnection)
    }

    private fun showMessengerIPCServiceToast() {
        messengerIPCService?.send(Message.obtain(null, MyMessengerIPCService.MSG_SHOW_TOAST,0,0).apply {
            data = bundleOf(MyMessengerIPCService.MSG_TOAST_TEXT to "toast text from MainActivity")
        })
    }

    private fun invokeAddMessengerIPCService() {
        messengerIPCService?.send(Message.obtain(null, MyMessengerIPCService.MSG_ADD_REQUEST,9,8))
    }
}