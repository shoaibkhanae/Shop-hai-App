package com.example.shophai.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shophai.R
import com.example.shophai.databinding.FragmentProfileBinding
import com.example.shophai.ui.auth.AuthActivity
import com.example.shophai.utils.SessionManager


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.apply {
            appBar.setNavigationOnClickListener { goToHomeFragment() }
            tvLogout.setOnClickListener { logout() }
        }
    }

    private fun goToHomeFragment() {
        findNavController().navigate(R.id.action_profileFragment_to_homeFragment)
    }

    private fun logout() {
        showProgressBar()
        clearAuthToken()
        showToast()
        goToAuthActivity()
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun clearAuthToken() {
        SessionManager.clearData(requireContext())
    }

    private fun showToast() {
        Toast.makeText(requireContext(), "Logout Successfully", Toast.LENGTH_SHORT).show()
    }

    private fun goToAuthActivity() {
        val intent = Intent(requireContext(), AuthActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}