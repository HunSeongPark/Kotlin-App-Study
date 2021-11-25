package com.hunseong.hilt.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.hunseong.hilt.R
import com.hunseong.hilt.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LogFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var logAdapter: LogAdapter

    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var hiBtn: Button

    private val viewModel: LogViewModel by viewModels()

    @Inject
    lateinit var navigator: Navigator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_log, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recycler_view)
        btn1 = view.findViewById(R.id.btn1)
        btn2 = view.findViewById(R.id.btn2)
        hiBtn = view.findViewById(R.id.btn_hi)

        bindViews()
        observeData()
        viewModel.getLogs()
    }

    private fun observeData() = viewModel.logLiveData.observe(viewLifecycleOwner) { list ->
        logAdapter.submitList(list)
    }


    private fun bindViews() {
        logAdapter = LogAdapter()
        recyclerView.adapter = logAdapter

        btn1.setOnClickListener {
            viewModel.add("Button 1 Clicked!")
        }

        btn2.setOnClickListener {
            viewModel.add("Button 2 Clicked!")
        }

        hiBtn.setOnClickListener {
            navigator.navigate(HiFragment())
        }
    }

}