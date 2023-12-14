package com.udacity.shoestore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.view.LoginFragmentDirections.fromLoginToOnboarding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = inflate(inflater, R.layout.fragment_login, container, false)
        addClickListeners()
        (activity as MainActivity?)?.menuVerifier()
        return binding.root
    }

    private fun addClickListeners() {
        binding.apply {
            buttonNewAccount.setOnClickListener { goToOnBoardingFragment() }
            buttonLogIn.setOnClickListener { goToOnBoardingFragment() }
        }
    }

    private fun goToOnBoardingFragment() = findNavController().navigate(fromLoginToOnboarding())
}