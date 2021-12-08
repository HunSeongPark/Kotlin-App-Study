package com.hunseong.workmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import com.hunseong.workmanager.databinding.ActivityMainBinding
import com.hunseong.workmanager.worker.SimpleSecondWorker
import com.hunseong.workmanager.worker.SimpleWorker
import com.hunseong.workmanager.worker.SimpleWorker.Companion.EXTRA_RESULT
import com.hunseong.workmanager.worker.SimpleWorker.Companion.TAG
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStartBtn()
        setCancelBtn()
    }

    private fun setStartBtn() {

        // Worker에 전달할 input Data
        val inputData = Data.Builder()
            .putInt(SimpleWorker.EXTRA_NUMBER, 5)
            .build()


        // Request에 설정할 제약조건
        // 제약조건에 해당하지 않을 경우, ENQUEUED 상태에 진입하여 제약조건이 충족될 때까지 대기
        val constraints = Constraints.Builder()
            .setRequiresDeviceIdle(true) // 기기가 IDLE 상태일 때 동작
            .setRequiresCharging(true) // 기기가 충전 중일 때 동작
            .build()



        // 한 번만 실행되는 task
        val simpleRequest = OneTimeWorkRequest.Builder(SimpleWorker::class.java)
            .setInputData(inputData)
            .setConstraints(constraints)
            .addTag(TAG)
            .build()

        val simpleSecondRequest = OneTimeWorkRequest.Builder(SimpleSecondWorker::class.java)
            .addTag(TAG)
            .setConstraints(constraints)
            .build()


        // 주기적으로 실행되는 task (본 request의 경우, 12시간마다 실행되는 task)
        val periodicRequest =
            PeriodicWorkRequest.Builder(SimpleWorker::class.java, 12, TimeUnit.HOURS)
                .setInputData(inputData).build()

        binding.startSimpleWorkerBtn.setOnClickListener {
            // WorkManager의 경우 Singleton 형태이므로, getInstance를 통해 싱글톤 객체 가져옴
            val workManager = WorkManager.getInstance()

            // workManager에 task 추가 (simpleRequest 이후 -> simpleSecondRequest)
            workManager.beginWith(simpleRequest).then(simpleSecondRequest).enqueue()

            // getWorkInfoByIdLiveData를 통해 request id에 해당하는 work의 status를 LiveData로 반환
            val status = workManager.getWorkInfoByIdLiveData(simpleRequest.id)
            status.observe(this) { info ->
                val workFinished = info.state.isFinished
                val result = info.outputData.getInt(EXTRA_RESULT, 0)
                binding.simpleWorkStatusText.text =
                    "work status: ${info.state}, result: $result, finished: $workFinished"
            }

            val statusSecond = workManager.getWorkInfoByIdLiveData(simpleSecondRequest.id)
            statusSecond.observe(this) { info ->
                val result = info.outputData.getInt(EXTRA_RESULT, 0)
                binding.simpleWork2StatusText.text = "work status: ${info.state}, result: $result"
            }
        }
    }

    private fun setCancelBtn() {
        binding.cancelSimpleWorkerBtn.setOnClickListener {
            val workManager = WorkManager.getInstance()

            // 모든 task cancel
            // workManager.cancelAllWork()

            // Tag에 해당되는 모든 task cancel
            workManager.cancelAllWorkByTag(TAG)

            // id에 해당하는 task cancel
//             workManager.cancelWorkById(id)
        }
    }
}