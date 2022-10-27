package com.ibrahimmakashi.pkartadmin.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ibrahimmakashi.pkartadmin.R
import com.ibrahimmakashi.pkartadmin.activity.AllOrderActivity
import com.ibrahimmakashi.pkartadmin.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.button2.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_categoryFragment)
        }
        binding.button3.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_productFragment)
        }
        binding.button4.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_slideFragment)
        }
        binding.button5.setOnClickListener{
            startActivity(Intent(requireContext(), AllOrderActivity::class.java))
        }


        return binding.root
    }

}