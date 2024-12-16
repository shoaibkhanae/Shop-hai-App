package com.example.shophai.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import coil.load
import com.example.shophai.MyApplication
import com.example.shophai.R
import com.example.shophai.databinding.FragmentProductBinding
import com.example.shophai.ui.viewmodel.HomeViewModel
import com.example.shophai.ui.viewmodel.MainViewModelFactory


class ProductFragment : Fragment() {
    private var _binding: FragmentProductBinding? = null
    val binding
        get() = _binding!!

    private val shareViewModel: HomeViewModel by activityViewModels {
        MainViewModelFactory((requireActivity().application as MyApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        sharedElementReturnTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        showProductDetails()
        binding.apply {
            btnBack.setOnClickListener { goToHomeFragment() }
            btnCart.setOnClickListener { goToCartFragment() }
        }
    }

    private fun showProductDetails() {
        shareViewModel.selected.observe(viewLifecycleOwner) { product ->
            binding.apply {
                ivProduct.apply {
                    transitionName = product.image
                    load(product.image)
                }
                tvProductName.text = product.title
                tvPrice.text = requireContext().getString(R.string.price, product.price.toString())
                tvRating.text = product.rating.rate.toString()
                tvReviews.text = requireContext().getString(R.string.d_reviews, product.rating.count)
                tvProductDescription.text = product.description
            }
        }
    }

    private fun goToHomeFragment() {
        findNavController().navigate(R.id.action_productFragment_to_homeFragment)
    }

    private fun goToCartFragment() {
        findNavController().navigate(R.id.action_productFragment_to_cartFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}