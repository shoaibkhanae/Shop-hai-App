package com.example.shophai.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.shophai.MyApplication
import com.example.shophai.R
import com.example.shophai.data.model.products.Products
import com.example.shophai.data.model.products.ProductsItem
import com.example.shophai.databinding.FragmentHomeBinding
import com.example.shophai.ui.viewmodel.HomeViewModel
import com.example.shophai.ui.viewmodel.MainViewModelFactory
import com.example.shophai.utils.Response

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!

    private val shareViewModel: HomeViewModel by activityViewModels {
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
        getCategoryProduct()
        setupUIWithCategory()
        binding.apply {
            avatarImage.setOnClickListener { goToProfileScreen() }
            searchIcon.setOnClickListener { goToSearchScreen() }
            cartIcon.setOnClickListener { goToCartScreen() }
        }
    }

    private fun setupUIWithCategory() {
        shareViewModel.products.observe(viewLifecycleOwner) {
            when(it) {
                is Response.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerview.visibility = View.VISIBLE

                    it.data?.let { products -> setupAdapter(products) }
                }
                is Response.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerview.visibility = View.GONE

                    Toast.makeText(requireContext(), "Error ${it.error}", Toast.LENGTH_LONG).show()
                }
                is Response.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerview.visibility = View.GONE
                }
            }
        }
    }

    private fun setupAdapter(products: Products) {
        val adapter = ProductAdapter(products)
        binding.recyclerview.adapter = adapter

        adapter.productSelectedListener = object : ProductAdapter.ProductSelectedListener {
            override fun productSelected(product: ProductsItem, imageView: ImageView) {
                shareViewModel.select(product)
                goToDetailScreenWithTransition(imageView, product)
            }
        }
    }

    private fun goToDetailScreenWithTransition(imageView: ImageView, product: ProductsItem) {
        val extras = FragmentNavigatorExtras(
            imageView to product.image
        )
        findNavController().navigate(
            R.id.action_homeFragment_to_productFragment,
            null,
            null,
            extras
        )
    }

    private fun goToProfileScreen() {
        findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
    }

    private fun goToSearchScreen() {
        findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
    }

    private fun goToCartScreen() {
        findNavController().navigate(R.id.action_homeFragment_to_cartFragment)
    }

    private fun getCategoryProduct() {
        binding.allChip.setOnClickListener {
            shareViewModel.getProducts()
        }
        binding.electronics.setOnClickListener {
            shareViewModel.getCategoryProducts("electronics")
        }
        binding.jewelery.setOnClickListener {
            shareViewModel.getCategoryProducts("jewelery")
        }
        binding.mensClothing.setOnClickListener {
            shareViewModel.getCategoryProducts("men's clothing")
        }
        binding.womensClothing.setOnClickListener {
            shareViewModel.getCategoryProducts("women's clothing")
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}