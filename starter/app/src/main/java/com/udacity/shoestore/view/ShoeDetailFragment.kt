package com.udacity.shoestore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodel.ShoeViewModel

class ShoeDetailFragment : Fragment() {
    private lateinit var viewModel: ShoeViewModel
    private lateinit var binding: FragmentShoeDetailBinding
    lateinit var shoe: Shoe

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        binding.shoeDetail = this
        viewModel = ViewModelProvider(requireActivity())[ShoeViewModel::class.java]
        addClickListeners()
        return binding.root
    }

    private fun addClickListeners() {
        shoe = Shoe()
        binding.apply {
            buttonCancel.setOnClickListener { popBackStack(it) }
            buttonSave.setOnClickListener {
                if (editTextShoeSize.text != null) {
                    shoe.size = editTextShoeSize.text.toString().toDouble()
                    viewModel.addNewShoe(shoe)
                    popBackStack(it)
                }
            }
        }
    }

    private fun popBackStack(it: View) {
        it.findNavController().popBackStack()
    }
}