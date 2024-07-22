package com.example.shophai.ui.home.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.shophai.databinding.FragmentHomeBinding
import com.example.shophai.ui.home.adapters.ProductAdapter
import com.example.shophai.ui.viewmodel.MainViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!

    private val shareViewModel: MainViewModel by activityViewModels()
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
        shareViewModel.products.observe(viewLifecycleOwner) {
            val adapter = ProductAdapter(it)
            binding.recyclerview.adapter = adapter
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}