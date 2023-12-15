package com.udacity.shoestore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainActivity
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodel.ShoeViewModel


class ShoeDetailFragment : Fragment() {
    private lateinit var viewModel: ShoeViewModel
    private lateinit var binding: FragmentShoeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        viewModel = ViewModelProvider(requireActivity())[ShoeViewModel::class.java]
        addClickListeners()
        (activity as MainActivity?)?.menuVerifier()
        return binding.root
    }

    private fun addClickListeners() {

        binding.apply {
            shoeDetail = this@ShoeDetailFragment
            shoeListViewModel = viewModel
            lifecycleOwner = this@ShoeDetailFragment
            shoe = Shoe()
        }

        viewModel.fromShoeDetailToShoeList.observe(viewLifecycleOwner) {
            if (it) {
                popBackStack()
                viewModel.onAddShoeComplete()
            }
        }

        viewModel.cancelFromShoeDetailToShoeList.observe(viewLifecycleOwner) {
            if (it) {
                popBackStack()
                viewModel.onCancelComplete()
            }
        }
    }

    private fun popBackStack() {
        findNavController().popBackStack()
    }
}