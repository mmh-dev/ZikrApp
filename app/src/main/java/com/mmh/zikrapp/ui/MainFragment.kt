package com.mmh.zikrapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.mmh.zikrapp.R
import com.mmh.zikrapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val binding: FragmentMainBinding by lazy {
        FragmentMainBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Zikr App"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            listOf(zikrBtn, tasbeehBtn, settingsBtn).forEach { button ->
                button.setOnClickListener {
                    when (button) {
                        zikrBtn -> findNavController().navigate(R.id.action_mainFragment_to_zikrFragment)
                        tasbeehBtn -> findNavController().navigate(R.id.action_mainFragment_to_tasbeehFragment)
                        settingsBtn -> findNavController().navigate(R.id.action_mainFragment_to_settingsFragment)
                    }
                }
            }
        }
    }
}