package com.example.shophai.ui.home.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.shophai.MyApplication
import com.example.shophai.R
import com.example.shophai.databinding.FragmentHomeBinding
import com.example.shophai.ui.home.adapters.ProductAdapter
import com.example.shophai.ui.viewmodel.MainViewModel
import com.example.shophai.ui.viewmodel.MainViewModelFactory

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!

    private val shareViewModel: MainViewModel by activityViewModels {
        MainViewModelFactory((requireActivity().application as MyApplication).repository)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setupUI()
        categoryProducts()
    }

    private fun setupUI() {
        shareViewModel.products.observe(viewLifecycleOwner) { products ->
            val adapter = ProductAdapter(products)
            binding.recyclerview.adapter = adapter
        }
    }

    private fun categoryProducts() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}