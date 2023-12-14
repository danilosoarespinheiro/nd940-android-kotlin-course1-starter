package com.udacity.shoestore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentOnboardingBinding
import com.udacity.shoestore.view.OnboardingFragmentDirections.fromOnboardingToInstructions

class OnboardingFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = inflate(inflater, R.layout.fragment_onboarding, container, false)
        binding.buttonProceed.setOnClickListener {
            findNavController().navigate(fromOnboardingToInstructions())
        }
        return binding.root
    }
}