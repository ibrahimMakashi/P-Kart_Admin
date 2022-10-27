package com.ibrahimmakashi.pkartadmin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ibrahimmakashi.pkartadmin.databinding.AllOrderItemLayoutBinding
import com.ibrahimmakashi.pkartadmin.model.AllOrderModel

class AllOrderAdapter(val list : ArrayList<AllOrderModel>, val context: Context)
    : RecyclerView.Adapter<AllOrderAdapter.AllOrderViewHolder>() {

    inner class AllOrderViewHolder(val binding : AllOrderItemLayoutBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllOrderViewHolder {
        return AllOrderViewHolder(
            AllOrderItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: AllOrderViewHolder, position: Int) {
        holder.binding.productTitle.text = list[position].name
        holder.binding.productPrice.text = list[position].price
        holder.binding.cancelButton.setOnClickListener {
//            holder.binding.proceedButton.text = "Canceled"
            holder.binding.proceedButton.visibility = GONE

            updateStatus("Canceled", list[position].orderId!!)
        }
        when(list[position].status){
            "Ordered" -> {
                holder.binding.proceedButton.text = "Dispatched"
                holder.binding.proceedButton.setOnClickListener {
                    updateStatus("Dispatched", list[position].orderId!!)
                }
            }
            "Dispatched" -> { holder.binding.proceedButton.text = "Delivered"
                holder.binding.proceedButton.setOnClickListener {
                    updateStatus("Delivered", list[position].orderId!!)
                }
              }
            "Delivered" -> {
                holder.binding.cancelButton.visibility = GONE
                holder.binding.proceedButton.isEnabled = false
                holder.binding.proceedButton.text = "Already Delivered"
              /*  holder.binding.cancelButton.setOnClickListener {
                    updateStatus("Canceled", list[position].orderId!!)
                }*/
            }
            "Canceled" -> {
                holder.binding.cancelButton.text = "Canceled"
                holder.binding.proceedButton.visibility = GONE
                holder.binding.cancelButton.isEnabled = false
            }
        }
    }

    fun updateStatus(str : String, doc : String){
        val data = hashMapOf<String, Any>()
        data["status"] = str

        Firebase.firestore.collection("allOrders")
            .document(doc).update(data).addOnSuccessListener {
                Toast.makeText(context, "Status updated", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(context, it.message , Toast.LENGTH_SHORT).show()
            }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}