package com.androidavid.prixmotors.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.androidavid.prixmotors.R

import com.androidavid.prixmotors.databinding.FragmentPolicyPrivacyBinding
import com.androidavid.prixmotors.viewmodel.MainViewModel


class FragmentPolicyPrivacy : Fragment(R.layout.fragment_policy_privacy) {

    private var _binding: FragmentPolicyPrivacyBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPolicyPrivacyBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)


        binding.privacyPolicyWebview.settings.javaScriptEnabled = true
        binding.privacyPolicyWebview.loadUrl(getString(R.string.url_privacy_policy_html))
    }


}