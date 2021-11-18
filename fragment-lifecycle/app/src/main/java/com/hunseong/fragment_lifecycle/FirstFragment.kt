package com.hunseong.fragment_lifecycle

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("FragmentLog", "FirstFragment - onAttach", )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("FragmentLog", "FirstFragment - onCreate", )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("FragmentLog", "FirstFragment - onCreateView", )
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("FragmentLog", "FirstFragment - onViewCreated", )

        view.findViewById<Button>(R.id.add_button).setOnClickListener {
            (requireActivity() as MainActivity).navigateTo(SecondFragment())
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.e("FragmentLog", "FirstFragment - onViewStateRestored", )
    }

    override fun onStart() {
        super.onStart()
        Log.e("FragmentLog", "FirstFragment - onStart", )
    }

    override fun onResume() {
        super.onResume()
        Log.e("FragmentLog", "FirstFragment - onResume", )
    }

    override fun onPause() {
        super.onPause()
        Log.e("FragmentLog", "FirstFragment - onPause", )
    }

    override fun onStop() {
        super.onStop()
        Log.e("FragmentLog", "FirstFragment - onStop", )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.e("FragmentLog", "FirstFragment - onSaveInstanceState", )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("FragmentLog", "FirstFragment - onDestroyView", )
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("FragmentLog", "FirstFragment - onDetach", )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("FragmentLog", "FirstFragment - onDestroy", )
    }
}