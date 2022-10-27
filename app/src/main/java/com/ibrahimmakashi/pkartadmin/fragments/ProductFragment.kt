package com.ibrahimmakashi.pkartadmin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ibrahimmakashi.pkartadmin.R
import com.ibrahimmakashi.pkartadmin.adapter.ProductAdapter
import com.ibrahimmakashi.pkartadmin.databinding.FragmentProductBinding
import com.ibrahimmakashi.pkartadmin.model.AddProductModel
import java.util.ArrayList

class ProductFragment : Fragment() {

    private lateinit var binding : FragmentProductBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductBinding.inflate(layoutInflater)


        getProducts()
        binding.floatingActionButton2.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_productFragment_to_addProductFragment)
        }

        return binding.root
    }



    private fun getProducts() {
        val list = ArrayList<AddProductModel>()
        Firebase.firestore.collection("products")
            .get().addOnSuccessListener {
                list.clear()
                for (doc in it.documents){
                    binding.progressBar2.visibility = VISIBLE
                    val data = doc.toObject(AddProductModel::class.java)
                    list.add(data!!)
                }
                binding.progressBar2.visibility = GONE
                binding.recyclerView.adapter = ProductAdapter(requireContext(), list)
            }
    }


}