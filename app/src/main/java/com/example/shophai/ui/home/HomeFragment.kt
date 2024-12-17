package com.example.shophai.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.shophai.R
import com.example.shophai.data.model.ProductsItem
import com.example.shophai.databinding.FragmentHomeBinding
import com.example.shophai.ui.adapter.ProductAdapter
import com.example.shophai.ui.viewmodel.HomeViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!

    private val shareViewModel: HomeViewModel by activityViewModels()

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
//        setupUIWithCategory()
        setupUI()
        binding.apply {
            avatarImage.setOnClickListener { goToProfileScreen() }
            searchIcon.setOnClickListener { goToSearchScreen() }
            cartIcon.setOnClickListener { goToCartScreen() }
        }
    }

//    private fun setupUIWithCategory() {
//        shareViewModel.products.observe(viewLifecycleOwner) {
//            when(it) {
//                is Response.Success -> {
//                    binding.progressBar.visibility = View.GONE
//                    binding.recyclerview.visibility = View.VISIBLE
//
//                    it.data?.let { products -> setupAdapter(products) }
//                }
//                is Response.Error -> {
//                    binding.progressBar.visibility = View.GONE
//                    binding.recyclerview.visibility = View.GONE
//
//                    Toast.makeText(requireContext(), "Error ${it.error}", Toast.LENGTH_LONG).show()
//                }
//                is Response.Loading -> {
//                    binding.progressBar.visibility = View.VISIBLE
//                    binding.recyclerview.visibility = View.GONE
//                }
//            }
//        }
//    }

    private fun setupUI() {
        shareViewModel.products.observe(viewLifecycleOwner) {
            Log.d("response", "${it.size}")
            setupAdapter(it)
        }
    }


    private fun setupAdapter(products: List<ProductsItem>) {
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
            shareViewModel.getProductsWithCategory("electronics")
        }
        binding.jewelery.setOnClickListener {
            shareViewModel.getProductsWithCategory("jewelery")
        }
        binding.mensClothing.setOnClickListener {
            shareViewModel.getProductsWithCategory("men's clothing")
        }
        binding.womensClothing.setOnClickListener {
            shareViewModel.getProductsWithCategory("women's clothing")
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}