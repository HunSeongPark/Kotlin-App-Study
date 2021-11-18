package com.hunseong.fragment_lifecycle

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class SecondFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("FragmentLog", "SecondFragment - onAttach", )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("FragmentLog", "SecondFragment - onCreate", )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("FragmentLog", "SecondFragment - onCreateView", )
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("FragmentLog", "SecondFragment - onViewCreated", )
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.e("FragmentLog", "SecondFragment - onViewStateRestored", )
    }

    override fun onStart() {
        super.onStart()
        Log.e("FragmentLog", "SecondFragment - onStart", )
    }

    override fun onResume() {
        super.onResume()
        Log.e("FragmentLog", "SecondFragment - onResume", )
    }

    override fun onPause() {
        super.onPause()
        Log.e("FragmentLog", "SecondFragment - onPause", )
    }

    override fun onStop() {
        super.onStop()
        Log.e("FragmentLog", "SecondFragment - onStop", )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.e("FragmentLog", "SecondFragment - onSaveInstanceState", )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("FragmentLog", "SecondFragment - onDestroyView", )
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("FragmentLog", "SecondFragment - onDetach", )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("FragmentLog", "SecondFragment - onDestroy", )
    }
}