package com.ibrahimmakashi.pkartadmin.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ibrahimmakashi.pkartadmin.R
import com.ibrahimmakashi.pkartadmin.adapter.AllOrderAdapter
import com.ibrahimmakashi.pkartadmin.model.AllOrderModel

class AllOrderActivity : AppCompatActivity() {
    private lateinit var list : ArrayList<AllOrderModel>
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_order)
        list = ArrayList()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewOrder)
        val progressBAr : ProgressBar = findViewById(R.id.progressBar4)

        Firebase.firestore.collection("allOrders").get().addOnSuccessListener {
            list.clear()
            for (doc in it){
                progressBAr.visibility = VISIBLE
                val data = doc.toObject(AllOrderModel::class.java)
                list.add(data)
            }
            progressBAr.visibility = GONE
            recyclerView.adapter = AllOrderAdapter(list, this)
        }

    }
}