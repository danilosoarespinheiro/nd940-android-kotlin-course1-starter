package com.udacity.shoestore.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.view.ShoeListFragmentDirections.fromShoesListToLogin
import com.udacity.shoestore.view.ShoeListFragmentDirections.fromShoesListToShoeDetails
import com.udacity.shoestore.viewmodel.ShoeViewModel


class ShoeListFragment : Fragment() {

    private lateinit var showViewModel: ShoeViewModel
    private lateinit var binding: FragmentShoeListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = inflate(inflater, R.layout.fragment_shoe_list, container, false)
        showViewModel = ViewModelProvider(requireActivity())[ShoeViewModel::class.java]
        addMenu()
        addObservers(inflater)
        addClickListeners()

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(fromShoesListToLogin())
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        return binding.root
    }

    private fun addClickListeners() {
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(fromShoesListToShoeDetails())
        }
    }

    private fun addObservers(inflater: LayoutInflater) {
        showViewModel.shoes.observe(viewLifecycleOwner) {
            if (it.size > 0) {
                for (shoe in it) binding.shoeList.addView(inflateShoeItemView(shoe, inflater))
            }
        }
    }

    @SuppressLint("StringFormatMatches")
    private fun inflateShoeItemView(shoe: Shoe, inflater: LayoutInflater): View {
        val shoeItemBinding: ShoeItemBinding = inflate(inflater, R.layout.shoe_item, null, false)

        shoeItemBinding.apply {
            textViewShoeName.text = getString(R.string.shoes_item_name, shoe.name)
            textViewShoeSize.text = getString(R.string.shoes_item_size, shoe.size)
            textViewCompany.text = getString(R.string.shoes_item_company, shoe.company)
            textViewShoeDescription.text =
                getString(R.string.shoes_item_description, shoe.description)
        }

        val layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        layoutParams.setMargins(4, 4, 4, 4)
        shoeItemBinding.root.layoutParams = layoutParams
        return shoeItemBinding.root
    }

    private fun addMenu() {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menu.clear()
                menuInflater.inflate(R.menu.menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.logout -> requireView().findNavController()
                        .navigate(fromShoesListToLogin())
                }
                return true
            }

            override fun onPrepareMenu(menu: Menu) {
                super.onPrepareMenu(menu)
                val item = menu.findItem(R.id.logout)
                item.isVisible = true
            }
        })
    }
}