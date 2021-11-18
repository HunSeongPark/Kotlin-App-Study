package com.hunseong.viewpager2.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hunseong.viewpager2.databinding.FragmentSplashPagerBinding

class SplashPagerFragment : Fragment() {

    private lateinit var string: String
    private lateinit var binding: FragmentSplashPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            string = requireArguments().getString(STRING_KEY, "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashPagerBinding.inflate(layoutInflater)

        binding.tv.text = string
        return binding.root
    }

    companion object {

        const val STRING_KEY = "string_key"

        fun newInstance(string: String) : SplashPagerFragment {
            val fragment = SplashPagerFragment()
            val bundle = Bundle()

            bundle.apply {
                putString(STRING_KEY, string)
            }

            fragment.arguments = bundle
            return fragment
        }
    }
}